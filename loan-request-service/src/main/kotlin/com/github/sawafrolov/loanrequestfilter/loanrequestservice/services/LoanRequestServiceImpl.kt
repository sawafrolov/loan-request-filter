package com.github.sawafrolov.loanrequestfilter.loanrequestservice.services

import com.github.sawafrolov.loanrequestfilter.loanrequestservice.mappers.LoanRequestMapper
import com.github.sawafrolov.loanrequestfilter.loanrequestservice.repositories.LoanRequestRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
@RequiredArgsConstructor
class LoanRequestServiceImpl(
    private val loanRequestMapper: LoanRequestMapper,
    private val loanRequestRepository: LoanRequestRepository,
    private val kafkaTemplate: KafkaTemplate<String, Any>
): LoanRequestService {

    @Value("\${kafka.loan-request-topic}")
    private lateinit var loanRequestTopic: String

    override fun submitLoanRequest(loanRequestId: UUID) {

        val loanRequest = loanRequestRepository
            .findById(loanRequestId)
            .orElseThrow {
                ResponseStatusException(HttpStatus.NOT_FOUND)
            }

        val loanRequestDto = loanRequestMapper.mapToDto(loanRequest)
        kafkaTemplate.send(loanRequestTopic, loanRequestDto).join()
    }
}
