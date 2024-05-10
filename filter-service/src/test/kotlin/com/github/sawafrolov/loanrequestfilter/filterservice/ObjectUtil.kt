package com.github.sawafrolov.loanrequestfilter.filterservice

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestCheckDto
import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto
import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import java.math.BigDecimal
import java.util.UUID

fun loanRequestDto() = LoanRequestDto(
    uuid = UUID.randomUUID(),
    amount = BigDecimal("1000000.00"),
    term = 3,
    title = "Test Title",
    fio = null,
    companyName = "Test Company",
    description = "some description",
    inn = "1212121212",
    capital = BigDecimal("500000.00"),
    regionNumber = 64,
    stopFactors = null,
    status = LoanRequestStatus.SUBMITTED,
    rejectReason = null,
    protectedFromChange = false
)

fun companyCorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("1000000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("500000.00"),
    regionNumber = 64
)

fun companyBorderValuesDto() = LoanRequestCheckDto(
    amount = BigDecimal("20000000.00"),
    term = 6,
    inn = "1212121212",
    capital = BigDecimal("8000000.00"),
    regionNumber = 64
)

fun companyIncorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("25000000.00"),
    term = 12,
    inn = "1212121212",
    capital = BigDecimal("8000000.00"),
    regionNumber = 64
)

fun humanCorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("1000000.00"),
    term = 18,
    inn = "121212121212",
    capital = BigDecimal("150000.00"),
    regionNumber = 64
)

fun humanBorderValuesDto() = LoanRequestCheckDto(
    amount = BigDecimal("2000000.00"),
    term = 24,
    inn = "121212121212",
    capital = BigDecimal("200000.00"),
    regionNumber = 64
)

fun humanIncorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("2500000.00"),
    term = 30,
    inn = "121212121212",
    capital = BigDecimal("150000.00"),
    regionNumber = 777
)

fun krasnoyarskCompanyCorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("2000000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("5500000.00"),
    regionNumber = 24
)

fun krasnoyarskCompanyBorderValuesDto() = LoanRequestCheckDto(
    amount = BigDecimal("2000000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("5000000.00"),
    regionNumber = 24
)

fun krasnoyarskCompanyIncorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("2000000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("4500000.00"),
    regionNumber = 24
)

fun nonResidentDto() = LoanRequestCheckDto(
    amount = BigDecimal("1000000.00"),
    term = 3,
    inn = "9909121212",
    capital = BigDecimal("500000.00"),
    regionNumber = 64
)
