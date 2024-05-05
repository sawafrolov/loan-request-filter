package com.github.sawafrolov.loanrequestfilter.commons.dto

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoanRequestRejectDto(

    @NotBlank
    val rejectReason: String
)
