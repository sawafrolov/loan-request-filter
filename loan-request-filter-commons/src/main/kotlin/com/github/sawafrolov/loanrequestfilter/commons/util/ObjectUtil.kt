package com.github.sawafrolov.loanrequestfilter.commons.util

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestCheckDto
import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto
import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import java.math.BigDecimal
import java.util.UUID

fun loanRequestCorrect(uuid: UUID?, companyId: UUID) = LoanRequest(
    uuid = uuid,
    companyId = companyId,
    title = "Test title",
    description = "Some description",
    amount = BigDecimal("1000000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("500000.00"),
    regionNumber = 64
)

fun loanRequestIncorrect(uuid: UUID, companyId: UUID) = LoanRequest(
    uuid = uuid,
    companyId = companyId,
    title = "Test title",
    description = "Some description",
    amount = BigDecimal("1000000.00"),
    term = 3,
    inn = "9909121212",
    capital = BigDecimal("500000.00"),
    regionNumber = 64
)

fun loanRequestCorrectDto(uuid: UUID, companyId: UUID) = LoanRequestDto(
    uuid = uuid,
    companyId = companyId,
    title = "Test title",
    description = "Some description",
    amount = BigDecimal("1000000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("500000.00"),
    regionNumber = 64
)

fun loanRequestIncorrectDto(uuid: UUID, companyId: UUID) = LoanRequestDto(
    uuid = uuid,
    companyId = companyId,
    title = "Test title",
    description = "Some description",
    amount = BigDecimal("1000000.00"),
    term = 3,
    inn = "9909121212",
    capital = BigDecimal("500000.00"),
    regionNumber = 64
)

fun correctDto() = LoanRequestCheckDto(
    uuid = UUID.randomUUID(),
    amount = BigDecimal("1000000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("500000.00"),
    regionNumber = 64
)

fun borderValuesDto() = LoanRequestCheckDto(
    uuid = UUID.randomUUID(),
    amount = BigDecimal("20000000.00"),
    term = 6,
    inn = "1212121212",
    capital = BigDecimal("8000000.00"),
    regionNumber = 64
)

fun incorrectDto() = LoanRequestCheckDto(
    uuid = UUID.randomUUID(),
    amount = BigDecimal("25000000.00"),
    term = 12,
    inn = "1212121212",
    capital = BigDecimal("8000000.00"),
    regionNumber = 64
)

fun krasnoyarskCorrectDto() = LoanRequestCheckDto(
    uuid = UUID.randomUUID(),
    amount = BigDecimal("2000000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("5500000.00"),
    regionNumber = 24
)

fun krasnoyarskBorderValuesDto() = LoanRequestCheckDto(
    uuid = UUID.randomUUID(),
    amount = BigDecimal("2000000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("5000000.00"),
    regionNumber = 24
)

fun krasnoyarskIncorrectDto() = LoanRequestCheckDto(
    uuid = UUID.randomUUID(),
    amount = BigDecimal("2000000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("4500000.00"),
    regionNumber = 24
)

fun nonResidentDto() = LoanRequestCheckDto(
    uuid = UUID.randomUUID(),
    amount = BigDecimal("1000000.00"),
    term = 3,
    inn = "9909121212",
    capital = BigDecimal("500000.00"),
    regionNumber = 64
)
