package com.github.sawafrolov.loanrequestfilter.commons.enums

/**
 * Статус рассмотрения заявки
 */
enum class LoanRequestStatus {

    /**
     * Черновик
     */
    DRAFT,

    /**
     * Обнаружены стоп-факторы
     */
    STOP_FACTORS,

    /**
     * На рассмотрении
     */
    IN_PROGRESS,

    /**
     * Одобрена
     */
    ACCEPTED,

    /**
     * Отказ
     */
    REJECTED
}
