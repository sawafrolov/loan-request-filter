package com.github.sawafrolov.loanrequestfilter.loanrequestservice.handlers

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestStopFactorsDto
import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import com.github.sawafrolov.loanrequestfilter.loanrequestservice.repositories.LoanRequestRepository
import com.github.sawafrolov.loanrequestfilter.loanrequestservice.util.findLoanRequestById
import lombok.RequiredArgsConstructor
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class StopFactorsKafkaHandler(
    private val loanRequestRepository: LoanRequestRepository
) {

    @KafkaListener(topics = ["\${kafka.loan-request-topic}"])
    fun handleStopFactors(stopFactorsDto: LoanRequestStopFactorsDto) {
        val loanRequest = loanRequestRepository.findLoanRequestById(stopFactorsDto.uuid)
        val stopFactors = stopFactorsDto.stopFactors
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
