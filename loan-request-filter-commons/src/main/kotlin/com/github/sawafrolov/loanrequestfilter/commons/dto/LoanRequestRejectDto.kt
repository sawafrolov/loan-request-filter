package com.github.sawafrolov.loanrequestfilter.commons.dto

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank

/**
 * DTO для отказа в выдаче кредита
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoanRequestRejectDto(

    /**
     * Причина отказа (обязательное поле)
     */
    @NotBlank
    val rejectReason: String
)
