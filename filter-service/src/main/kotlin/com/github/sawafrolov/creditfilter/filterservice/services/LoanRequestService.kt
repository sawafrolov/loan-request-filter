package com.github.sawafrolov.creditfilter.filterservice.services

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto

interface LoanRequestService {

    fun submitLoanRequest(loanRequestDto: LoanRequestDto)
}
