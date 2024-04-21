package com.github.sawafrolov.loanrequestfilter.loanrequestservice.projections

import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import org.springframework.data.rest.core.config.Projection
import java.math.BigDecimal
import java.util.UUID

@Projection(name = "loanRequestInfo", types = [LoanRequest::class])
interface LoanRequestInfo {

    fun getUuid(): UUID?

    fun getAmount(): BigDecimal

    fun getTerm(): Int

    fun getTitle(): String

    fun getFio(): String

    fun getCompanyName(): String

    fun getDescription(): String

    fun getInn(): String

    fun getCapital(): BigDecimal

    fun getRegionNumber(): Int

    fun getStopFactors(): String

    fun getStatus(): LoanRequestStatus

    fun getRejectReason(): String
}
