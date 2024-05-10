package com.github.sawafrolov.loanrequestfilter.filterservice.handlers

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto
import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import com.github.sawafrolov.loanrequestfilter.filterservice.mappers.LoanRequestMapper
import com.github.sawafrolov.loanrequestfilter.filterservice.services.LoanRequestValidationService
import com.github.sawafrolov.loanrequestfilter.starter.jpa.repositories.LoanRequestRepository
import lombok.RequiredArgsConstructor
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@RequiredArgsConstructor
class LoanRequestKafkaHandler(
    private val loanRequestMapper: LoanRequestMapper,
    private val loanRequestRepository: LoanRequestRepository,
    private val loanRequestValidationService: LoanRequestValidationService
) {

    @Transactional
    @KafkaListener(topics = ["\${kafka.loan-request-topic}"])
    fun handleLoanRequest(loanRequestDto: LoanRequestDto) {
        if (loanRequestDto.status != LoanRequestStatus.SUBMITTED) {
            throw IllegalArgumentException("Loan request must have status SUBMITTED")
        }

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
