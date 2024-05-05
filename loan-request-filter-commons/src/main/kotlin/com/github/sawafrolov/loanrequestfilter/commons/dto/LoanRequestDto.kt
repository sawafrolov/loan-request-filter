package com.github.sawafrolov.loanrequestfilter.commons.dto

import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.util.UUID

/**
 * DTO заявки на кредит
 */
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
    val regionNumber: Int,

    /**
     * Список факторов, препятствующих рассмотрению заявки
     */
    var stopFactors: String?,

    /**
     * Статус рассмотрения заявки
     */
    @NotNull
    var status: LoanRequestStatus = LoanRequestStatus.DRAFT,

    /**
     * Причина отказа в выдаче кредита
     * (обязательно должна быть заполнена в случае отказа)
     */
    var rejectReason: String?,

    /**
     * read-only (служебное поле базы данных)
     */
    @NotNull
    var protectedFromChange: Boolean = false
)
