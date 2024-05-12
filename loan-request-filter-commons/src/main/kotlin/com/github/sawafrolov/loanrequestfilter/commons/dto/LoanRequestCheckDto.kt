package com.github.sawafrolov.loanrequestfilter.commons.dto

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.hibernate.validator.constraints.Length
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import java.util.UUID

/**
 * DTO для проверки заявки с помощью Camunda
 */
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoanRequestCheckDto(

    /**
     * UUID
     */
    val uuid: UUID,

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
     * ИНН компании
     */
    @NotBlank
    @Length(min = 10, max = 10, message = "ИНН компании должен состоять из 10 цифр")
    val inn: String,

    /**
     * Общая сумма денег на счетах компании
     */
    @Positive
    val capital: BigDecimal,

    /**
     * Код региона
     */
    @Positive
    val regionNumber: Int
)
