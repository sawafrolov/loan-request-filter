package com.github.sawafrolov.loanrequestfilter.filterservice

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestCheckDto
import java.math.BigDecimal

fun companyCorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("1_000_000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("500_000.00"),
    regionNumber = 64
)

fun companyBorderValuesDto() = LoanRequestCheckDto(
    amount = BigDecimal("20_000_000.00"),
    term = 6,
    inn = "1212121212",
    capital = BigDecimal("8_000_000.00"),
    regionNumber = 64
)

fun companyIncorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("25_000_000.00"),
    term = 12,
    inn = "1212121212",
    capital = BigDecimal("8_000_000.00"),
    regionNumber = 64
)

fun humanCorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("1_000_000.00"),
    term = 18,
    inn = "121212121212",
    capital = BigDecimal("150_000.00"),
    regionNumber = 64
)

fun humanBorderValuesDto() = LoanRequestCheckDto(
    amount = BigDecimal("2_000_000.00"),
    term = 24,
    inn = "1212121212",
    capital = BigDecimal("200_000.00"),
    regionNumber = 64
)

fun humanIncorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("2_500_000.00"),
    term = 30,
    inn = "1212121212",
    capital = BigDecimal("150_000.00"),
    regionNumber = 777
)

fun krasnoyarskCompanyCorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("2_000_000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("5_500_000.00"),
    regionNumber = 24
)

fun krasnoyarskCompanyBorderValuesDto() = LoanRequestCheckDto(
    amount = BigDecimal("2_000_000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("5_000_000.00"),
    regionNumber = 24
)

fun krasnoyarskCompanyIncorrectDto() = LoanRequestCheckDto(
    amount = BigDecimal("2_000_000.00"),
    term = 3,
    inn = "1212121212",
    capital = BigDecimal("4_500_000.00"),
    regionNumber = 24
)

fun nonResidentDto() = LoanRequestCheckDto(
    amount = BigDecimal("1_000_000.00"),
    term = 3,
    inn = "9909121212",
    capital = BigDecimal("500_000.00"),
    regionNumber = 64
)
