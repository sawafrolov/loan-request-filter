package com.github.sawafrolov.loanrequestfilter.commons.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.validation.annotation.Validated
import java.util.UUID

/**
 * DTO для передачи стоп-факторов заявки на кредит
 */
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoanRequestStopFactorsDto(

    /**
     * UUID
     */
    val uuid: UUID,

    /**
     * Стоп-факторы
     */
    val stopFactors: List<String>
)
