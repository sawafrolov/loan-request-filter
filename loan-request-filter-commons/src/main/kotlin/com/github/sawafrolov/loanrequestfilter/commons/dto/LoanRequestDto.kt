package com.github.sawafrolov.loanrequestfilter.commons.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.hibernate.validator.constraints.Length
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
     * UUID компании
     */
    val companyId: UUID,

    /**
     * Заголовок заявки
     */
    @NotBlank
    val title: String,

    /**
     * Описание заявки
     */
    @NotBlank
    val description: String,

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
    val regionNumber: Int,

    /**
     * Статус рассмотрения заявки
     */
    val status: LoanRequestStatus = LoanRequestStatus.DRAFT,

    /**
     * Список факторов, препятствующих рассмотрению заявки
     */
    val stopFactors: String? = null,

    /**
     * Причина отказа в выдаче кредита
     * (обязательно должна быть заполнена в случае отказа)
     */
    val rejectReason: String? = null,

    /**
     * read-only (служебное поле базы данных)
     */
    val protectedFromChange: Boolean = false,

    /**
     * Пометка об удалении
     */
    val deleted: Boolean = false
)
