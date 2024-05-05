package com.github.sawafrolov.loanrequestfilter.loanrequestservice.services

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto
import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
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
        val loanRequest = findLoanRequestById(loanRequestId)
        val loanRequestDto = loanRequestMapper.mapToDto(loanRequest)
        kafkaTemplate.send(loanRequestTopic, loanRequestDto).join()
    }

    override fun acceptLoanRequest(loanRequestId: UUID): LoanRequestDto {
        val loanRequest = findLoanRequestById(loanRequestId)
        loanRequest.status = LoanRequestStatus.ACCEPTED
        val result = loanRequestRepository.save(loanRequest)
        return loanRequestMapper.mapToDto(result)
    }

    override fun rejectLoanRequest(loanRequestId: UUID, rejectReason: String): LoanRequestDto {
        val loanRequest = findLoanRequestById(loanRequestId)
        loanRequest.status = LoanRequestStatus.REJECTED
        loanRequest.rejectReason = rejectReason
        val result = loanRequestRepository.save(loanRequest)
        return loanRequestMapper.mapToDto(result)
    }

    private fun findLoanRequestById(loanRequestId: UUID): LoanRequest =
        loanRequestRepository
            .findById(loanRequestId)
            .orElseThrow {
                ResponseStatusException(HttpStatus.NOT_FOUND)
            }
}
