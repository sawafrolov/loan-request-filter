package com.github.sawafrolov.loanrequestfilter.loanrequestservice.util

import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import com.github.sawafrolov.loanrequestfilter.loanrequestservice.repositories.LoanRequestRepository
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

fun LoanRequestRepository.findLoanRequestById(loanRequestId: UUID): LoanRequest =
    this.findById(loanRequestId)
        .orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
