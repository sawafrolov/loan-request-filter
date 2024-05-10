package com.github.sawafrolov.loanrequestfilter.commons.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import java.util.UUID

/**
 * DTO заявки на кредит
 */
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoanRequestDto(

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
     * Название заявки
     */
    @NotBlank
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
    val regionNumber: Int,

    /**
     * Список факторов, препятствующих рассмотрению заявки
     */
    var stopFactors: String?,

    /**
     * Статус рассмотрения заявки
     */
    var status: LoanRequestStatus,

    /**
     * Причина отказа в выдаче кредита
     * (обязательно должна быть заполнена в случае отказа)
     */
    var rejectReason: String?,

    /**
     * read-only (служебное поле базы данных)
     */
    var protectedFromChange: Boolean
)
