package com.github.sawafrolov.loanrequestfilter.loanrequestservice.projections

import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import org.springframework.data.rest.core.config.Projection
import java.math.BigDecimal
import java.util.UUID

@Projection(name = "loanRequestInfo", types = [LoanRequest::class])
interface LoanRequestInfo {

    fun getUuid(): UUID?

    fun getCompanyId(): UUID

    fun getTitle(): String

    fun getDescription(): String

    fun getAmount(): BigDecimal

    fun getTerm(): Int

    fun getInn(): String

    fun getCapital(): BigDecimal

    fun getRegionNumber(): Int

    fun getStatus(): LoanRequestStatus

    fun getStopFactors(): String?

    fun getRejectReason(): String?

    fun getProtectedFromChange(): Boolean

    fun getDeleted(): Boolean
}
