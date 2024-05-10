package com.github.sawafrolov.loanrequestfilter.commons.dto

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated

/**
 * DTO для отказа в выдаче кредита
 */
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoanRequestRejectDto(

    /**
     * Причина отказа (обязательное поле)
     */
    @NotBlank
    val rejectReason: String
)
