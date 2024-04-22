package com.github.sawafrolov.loanrequestfilter.filterservice.services

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto

interface OrderValidationService {

    fun checkStopFactors(loanRequestDto: LoanRequestDto): List<String>
}
