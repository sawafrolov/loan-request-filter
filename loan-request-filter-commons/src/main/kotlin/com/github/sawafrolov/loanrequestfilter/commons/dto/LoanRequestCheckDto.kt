package com.github.sawafrolov.loanrequestfilter.commons.dto

import lombok.Builder
import java.math.BigDecimal

@Builder
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
