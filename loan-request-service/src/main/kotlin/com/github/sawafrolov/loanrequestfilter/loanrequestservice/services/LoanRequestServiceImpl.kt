package com.github.sawafrolov.loanrequestfilter.loanrequestservice.services

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto
import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import com.github.sawafrolov.loanrequestfilter.loanrequestservice.mappers.LoanRequestMapper
import com.github.sawafrolov.loanrequestfilter.loanrequestservice.repositories.LoanRequestRepository
import com.github.sawafrolov.loanrequestfilter.loanrequestservice.util.findLoanRequestById
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.UUID

@Service
@RequiredArgsConstructor
class LoanRequestServiceImpl(
    private val loanRequestMapper: LoanRequestMapper,
    private val loanRequestRepository: LoanRequestRepository
): LoanRequestService {

    override fun acceptLoanRequest(loanRequestId: UUID): LoanRequestDto {
        val loanRequest = loanRequestRepository.findLoanRequestById(loanRequestId)
        loanRequest.status = LoanRequestStatus.ACCEPTED
        val result = loanRequestRepository.save(loanRequest)
        return loanRequestMapper.mapToDto(result)
    }

    override fun rejectLoanRequest(loanRequestId: UUID, rejectReason: String): LoanRequestDto {
        val loanRequest = loanRequestRepository.findLoanRequestById(loanRequestId)
        loanRequest.status = LoanRequestStatus.REJECTED
        loanRequest.rejectReason = rejectReason
        val result = loanRequestRepository.save(loanRequest)
        return loanRequestMapper.mapToDto(result)
    }
}
