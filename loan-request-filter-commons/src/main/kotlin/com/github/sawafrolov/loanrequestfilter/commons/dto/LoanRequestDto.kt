package com.github.sawafrolov.loanrequestfilter.commons.dto

import java.math.BigDecimal
import java.util.UUID

data class LoanRequestDto(

    /**
     * UUID
     */
    val uuid: UUID,

    /**
     * Размер кредита
     */
    val amount: BigDecimal,

    /**
     * Срок кредита в месяцах
     */
    val term: Int,

    /**
     * Название заявки
     */
    val title: String,

    /**
     * ФИО физлица
     */
    val fio: String?,

    /**
     * Название компании
     */
    val companyName: String?,

    /**
     * Описание заявки
     */
    val description: String?,

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
