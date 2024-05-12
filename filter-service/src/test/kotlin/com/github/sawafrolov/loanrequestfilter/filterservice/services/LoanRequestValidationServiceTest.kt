package com.github.sawafrolov.loanrequestfilter.filterservice.services

import com.github.sawafrolov.loanrequestfilter.commons.util.borderValuesDto
import com.github.sawafrolov.loanrequestfilter.commons.util.correctDto
import com.github.sawafrolov.loanrequestfilter.commons.util.incorrectDto
import com.github.sawafrolov.loanrequestfilter.commons.util.krasnoyarskBorderValuesDto
import com.github.sawafrolov.loanrequestfilter.commons.util.krasnoyarskCorrectDto
import com.github.sawafrolov.loanrequestfilter.commons.util.krasnoyarskIncorrectDto
import com.github.sawafrolov.loanrequestfilter.commons.util.nonResidentDto

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
        assertTrue(loanRequestValidationService.checkStopFactors(correctDto()).isEmpty())
    }

    @Test
    @DisplayName("Test company border values")
    fun testCompanyBorderValues() {
        assertTrue(loanRequestValidationService.checkStopFactors(borderValuesDto()).isEmpty())
    }

    @Test
    @DisplayName("Test company incorrect")
    fun testCompanyIncorrect() {
        val stopFactors = loanRequestValidationService.checkStopFactors(incorrectDto())
        assertFalse(stopFactors.isEmpty())
        assertEquals(3, stopFactors.size)
        assertTrue(stopFactors.contains("Кредиты выдаются компаниям не более чем на 6 месяцев"))
        assertTrue(stopFactors.contains("Кредиты выдаются компаниям не более чем на 20 миллионов рублей"))
        assertTrue(stopFactors.contains("Капитал компании должен превышать 40 процентов от запрашиваемой суммы"))
    }

    @Test
    @DisplayName("Test krasnoyarsk company correct")
    fun testKrasnoyarskCompanyCorrect() {
        assertTrue(loanRequestValidationService.checkStopFactors(krasnoyarskCorrectDto()).isEmpty())
    }

    @Test
    @DisplayName("Test krasnoyarsk company border values")
    fun testKrasnoyarskCompanyBorderValues() {
        assertTrue(loanRequestValidationService.checkStopFactors(krasnoyarskBorderValuesDto()).isEmpty())
    }

    @Test
    @DisplayName("Test krasnoyarsk company incorrect")
    fun testKrasnoyarskCompanyIncorrect() {
        val stopFactors = loanRequestValidationService.checkStopFactors(krasnoyarskIncorrectDto())
        assertFalse(stopFactors.isEmpty())
        assertEquals(1, stopFactors.size)
        assertTrue(stopFactors.contains("Капитал компании из Красноярского края должен быть больше 5 миллионов рублей"))
    }

    @Test
    @DisplayName("Test non-resident company")
    fun testNonresident() {
        val stopFactors = loanRequestValidationService.checkStopFactors(nonResidentDto())
        assertFalse(stopFactors.isEmpty())
        assertEquals(1, stopFactors.size)
        assertTrue(stopFactors.contains("Кредиты не выдаются нерезидентам РФ"))
    }
}
