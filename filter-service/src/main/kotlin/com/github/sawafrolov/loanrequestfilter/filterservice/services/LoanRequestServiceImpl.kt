package com.github.sawafrolov.loanrequestfilter.filterservice.services

import com.github.sawafrolov.loanrequestfilter.filterservice.mappers.LoanRequestMapper
import com.github.sawafrolov.loanrequestfilter.filterservice.repositories.LoanRequestRepository
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
    private val loanRequestValidationService: LoanRequestValidationService,
): LoanRequestService {

    @Transactional
    @KafkaListener(topics = ["\${loan-request-filter.loan-request-topic}"])
    override fun submitLoanRequest(loanRequestDto: LoanRequestDto) {
        val loanRequest = loanRequestMapper.mapToEntity(loanRequestDto)
        val loanRequestCheckDto = loanRequestMapper.mapToCheckDto(loanRequestDto)
        val stopFactors = loanRequestValidationService.checkStopFactors(loanRequestCheckDto)
        if (stopFactors.isEmpty()) {
            loanRequest.stopFactors = null
            loanRequest.status = LoanRequestStatus.IN_PROGRESS
            loanRequest.protectedFromChange = true
        } else {
            loanRequest.stopFactors = stopFactors.joinToString()
            loanRequest.status = LoanRequestStatus.STOP_FACTORS
        }
        loanRequestRepository.save(loanRequest)
    }
}
