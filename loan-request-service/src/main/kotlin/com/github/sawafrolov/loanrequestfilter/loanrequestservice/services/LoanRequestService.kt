package com.github.sawafrolov.loanrequestfilter.loanrequestservice.services

import java.util.UUID

interface LoanRequestService {

    fun submitLoanRequest(loanRequestId: UUID)
}