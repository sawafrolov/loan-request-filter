package com.github.sawafrolov.loanrequestfilter.filterservice.handlers

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestCheckDto
import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestStopFactorsDto
import com.github.sawafrolov.loanrequestfilter.filterservice.services.LoanRequestValidationService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class LoanRequestKafkaHandler(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    private val loanRequestValidationService: LoanRequestValidationService
) {

    @Value("\${kafka.stop-factors-topic}")
    private lateinit var stopFactorsTopic: String

    @KafkaListener(topics = ["\${kafka.loan-request-topic}"])
    fun handleLoanRequest(loanRequestCheckDto: LoanRequestCheckDto) {
        val stopFactors = loanRequestValidationService.checkStopFactors(loanRequestCheckDto)
        val stopFactorsDto = LoanRequestStopFactorsDto(loanRequestCheckDto.uuid, stopFactors)
        kafkaTemplate.send(stopFactorsTopic, stopFactorsDto).join()
    }
}
