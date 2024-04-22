package com.github.sawafrolov.creditfilter.filterservice.services

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto

interface OrderValidationService {

    fun checkStopFactors(loanRequestDto: LoanRequestDto): List<String>
}
