package com.github.sawafrolov.loanrequestfilter.filterservice

import com.github.sawafrolov.loanrequestfilter.filterservice.services.LoanRequestValidationService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LoanRequestValidationServiceTest(
    @Autowired val loanRequestValidationService: LoanRequestValidationService
) {

    @Test
    @DisplayName("Test company correct")
    fun testCompanyCorrect() {
        assertTrue(loanRequestValidationService.checkStopFactors(companyCorrectDto()).isEmpty())
    }

    @Test
    @DisplayName("Test company border values")
    fun testCompanyBorderValues() {
        assertTrue(loanRequestValidationService.checkStopFactors(companyBorderValuesDto()).isEmpty())
    }

    @Test
    @DisplayName("Test company incorrect")
    fun testCompanyIncorrect() {
        assertTrue(loanRequestValidationService.checkStopFactors(companyIncorrectDto()).isEmpty())
    }
}
