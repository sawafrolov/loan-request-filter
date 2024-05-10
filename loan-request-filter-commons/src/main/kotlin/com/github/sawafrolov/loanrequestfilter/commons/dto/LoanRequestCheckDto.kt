package com.github.sawafrolov.loanrequestfilter.commons.dto

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

/**
 * DTO для проверки заявки с помощью Camunda
 */
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoanRequestCheckDto(

    /**
     * Размер кредита
     */
    @Positive
    val amount: BigDecimal,

    /**
     * Срок кредита в месяцах
     */
    @Positive
    val term: Int,

    /**
     * ИНН компании или физлица
     */
    @NotBlank
    @Pattern(
        regexp = "^\\d{10}(\\d{2})?\$",
        message = "ИНН должен состоять из 10 или 12 цифр"
    )
    val inn: String,

    /**
     * Общая сумма денег на счетах компании или физлица
     */
    @Positive
    val capital: BigDecimal,

    /**
     * Код региона
     */
    @Positive
    val regionNumber: Int
)
