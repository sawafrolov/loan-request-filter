package com.github.sawafrolov.loanrequestfilter.filterservice.services

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestCheckDto

interface LoanRequestValidationService {

    fun checkStopFactors(loanRequestCheckDto: LoanRequestCheckDto): List<String>
}
