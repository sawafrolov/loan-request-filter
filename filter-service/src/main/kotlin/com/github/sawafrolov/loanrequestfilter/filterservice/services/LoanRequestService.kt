package com.github.sawafrolov.loanrequestfilter.filterservice.services

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto

interface LoanRequestService {

    fun submitLoanRequest(loanRequestDto: LoanRequestDto)
}
