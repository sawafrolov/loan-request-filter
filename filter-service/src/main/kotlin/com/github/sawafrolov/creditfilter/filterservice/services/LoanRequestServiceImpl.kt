package com.github.sawafrolov.creditfilter.filterservice.services

import com.github.sawafrolov.creditfilter.filterservice.mappers.LoanRequestMapper
import com.github.sawafrolov.creditfilter.filterservice.repositories.ElasticSearchLoanRequestRepository
import com.github.sawafrolov.creditfilter.filterservice.repositories.LoanRequestRepository
import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto
import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import lombok.RequiredArgsConstructor
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class LoanRequestServiceImpl(
    private val loanRequestMapper: LoanRequestMapper,
    private val loanRequestRepository: LoanRequestRepository,
    private val orderValidationService: OrderValidationService,
    private val elasticSearchLoanRequestRepository: ElasticSearchLoanRequestRepository
): LoanRequestService {

    @KafkaListener(topics = ["\${loan-request-filter.loan-request-topic}"])
    @Transactional
    override fun submitLoanRequest(loanRequestDto: LoanRequestDto) {
        val loanRequest = loanRequestMapper.mapToEntity(loanRequestDto)
        val stopFactors = orderValidationService.checkStopFactors(loanRequestDto)
        if (stopFactors.isEmpty()) {
            loanRequest.stopFactors = null
            loanRequest.status = LoanRequestStatus.IN_PROGRESS
            loanRequest.protectedFromChange = true
            loanRequestRepository.save(loanRequest)
            val loanRequestDocument = loanRequestMapper.mapToDocument(loanRequestDto)
            elasticSearchLoanRequestRepository.save(loanRequestDocument)
        } else {
            loanRequest.stopFactors = stopFactors.joinToString()
            loanRequest.status = LoanRequestStatus.STOP_FACTORS
            loanRequestRepository.save(loanRequest)
        }
    }
}