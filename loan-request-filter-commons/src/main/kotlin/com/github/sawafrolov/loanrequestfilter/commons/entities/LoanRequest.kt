package com.github.sawafrolov.loanrequestfilter.commons.entities

import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import org.hibernate.validator.constraints.UUID
import java.math.BigDecimal

/**
 * Сущность заявки на кредит
 */
@Getter
@Setter
@QueryEntity
@Entity(name = "loan-request")
@SQLDelete(sql = "UPDATE loan-request SET deleted=true WHERE uuid=?")
@Where(clause = "deleted=false")
class LoanRequest(

    /**
     * Размер кредита
     */
    @Positive
    @Column(name = "amount")
    val amount: BigDecimal,

    /**
     * Срок кредита в месяцах
     */
    @Positive
    @Column(name = "term")
    val term: Int,

    /**
     * Название заявки
     */
    @NotBlank
    @Column(name = "title")
    val title: String,

    /**
     * ФИО физлица
     */
    @Column(name = "fio")
    val fio: String?,

    /**
     * Название компании
     */
    @Column(name = "company_name")
    val companyName: String?,

    /**
     * Описание заявки
     */
    @Column(name = "description")
    val description: String?,

    /**
     * ИНН компании или физлица
     */
    @NotBlank
    @Pattern(
        regexp = "^\\d{10}(\\d{2})?\$",
        message = "ИНН должен состоять из 10 или 12 цифр"
    )
    @Column(name = "inn")
    val inn: String,

    /**
     * Общая сумма денег на счетах компании или физлица
     */
    @Positive
    @Column(name = "capital")
    val capital: BigDecimal,

    /**
     * Код региона
     */
    @Positive
    @Column(name = "region_number")
    val regionNumber: Int,

    /**
     * Список факторов, препятствующих рассмотрению заявки
     */
    @Column(name = "stop_factors")
    var stopFactors: String?,

    /**
     * Статус рассмотрения заявки
     */
    @Column(name = "status")
    var status: LoanRequestStatus = LoanRequestStatus.DRAFT,

    /**
     * Причина отказа в выдаче кредита
     * (обязательно должна быть заполнена в случае отказа)
     */
    @Column(name = "reject_reason")
    var rejectReason: String?,

    /**
     * read-only (служебное поле базы данных)
     */
    @Column(name = "protected_from_change", nullable = false, columnDefinition = "boolean default false")
    var protectedFromChange: Boolean = false,

    /**
     * Пометка на удаление
     */
    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    var deleted: Boolean = false,

    /**
     * UUID
     */
    @Id
    @UUID
    @Column(name = "uuid", updatable = false, nullable = false)
    val uuid: java.util.UUID? = null
)
