package com.github.sawafrolov.loanrequestfilter.loanrequestservice.services

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto
import java.util.UUID

interface LoanRequestService {

    fun submitLoanRequest(loanRequestId: UUID)

    fun acceptLoanRequest(loanRequestId: UUID): LoanRequestDto

    fun rejectLoanRequest(loanRequestId: UUID, rejectReason: String): LoanRequestDto
}
