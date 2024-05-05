package com.github.sawafrolov.loanrequestfilter.commons.dto

import java.math.BigDecimal

/**
 * DTO для проверки заявки с помощью Camunda
 */
data class LoanRequestCheckDto(

    /**
     * Размер кредита
     */
    val amount: BigDecimal,

    /**
     * Срок кредита в месяцах
     */
    val term: Int,

    /**
     * ИНН компании или физлица
     */
    val inn: String,

    /**
     * Общая сумма денег на счетах компании или физлица
     */
    val capital: BigDecimal,

    /**
     * Код региона
     */
    val regionNumber: Int
)
