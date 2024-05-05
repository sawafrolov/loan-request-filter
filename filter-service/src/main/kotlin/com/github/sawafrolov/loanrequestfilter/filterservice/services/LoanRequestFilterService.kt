package com.github.sawafrolov.loanrequestfilter.filterservice.services

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto

interface LoanRequestFilterService {

    fun submitLoanRequest(loanRequestDto: LoanRequestDto)
}
