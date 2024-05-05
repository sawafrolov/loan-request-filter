package com.github.sawafrolov.loanrequestfilter.loanrequestservice.handlers

import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import com.github.sawafrolov.loanrequestfilter.loanrequestservice.mappers.LoanRequestMapper
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.annotation.HandleAfterSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
@RepositoryEventHandler
@RequiredArgsConstructor
class LoanRequestRepositoryEventHandler(
    private val loanRequestMapper: LoanRequestMapper,
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    @Value("\${kafka.loan-request-topic}")
    private lateinit var loanRequestTopic: String

    @HandleAfterSave
    fun handleAfterSave(loanRequest: LoanRequest) {
        if (loanRequest.status == LoanRequestStatus.SUBMITTED) {
            val loanRequestDto = loanRequestMapper.mapToDto(loanRequest)
            kafkaTemplate.send(loanRequestTopic, loanRequestDto).join()
        }
    }
}
