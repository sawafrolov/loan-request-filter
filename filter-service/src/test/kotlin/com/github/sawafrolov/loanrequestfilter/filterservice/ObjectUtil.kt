package com.github.sawafrolov.loanrequestfilter.filterservice

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto
import java.math.BigDecimal
import java.util.UUID

fun correctDto() = LoanRequestDto(
    UUID.randomUUID(), BigDecimal.ZERO, 4, "", "","", "","1212121212", BigDecimal("123456789.12"), 12
)

fun ipDto() = LoanRequestDto(
    UUID.randomUUID(), BigDecimal.ZERO, 4, "", "","", "","121212121212", BigDecimal("123456789.12"), 12
)

fun krasnoyarskDto() = LoanRequestDto(
    UUID.randomUUID(), BigDecimal.ZERO, 4, "", "","", "","1212121212", BigDecimal("123456789.12"), 24
)
