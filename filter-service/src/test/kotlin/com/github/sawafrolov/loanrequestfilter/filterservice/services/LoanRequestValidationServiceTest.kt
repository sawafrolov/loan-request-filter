package com.github.sawafrolov.loanrequestfilter.filterservice.services

import com.github.sawafrolov.loanrequestfilter.filterservice.companyBorderValuesDto
import com.github.sawafrolov.loanrequestfilter.filterservice.companyCorrectDto
import com.github.sawafrolov.loanrequestfilter.filterservice.companyIncorrectDto
import com.github.sawafrolov.loanrequestfilter.filterservice.humanBorderValuesDto
import com.github.sawafrolov.loanrequestfilter.filterservice.humanCorrectDto
import com.github.sawafrolov.loanrequestfilter.filterservice.humanIncorrectDto
import com.github.sawafrolov.loanrequestfilter.filterservice.krasnoyarskCompanyBorderValuesDto
import com.github.sawafrolov.loanrequestfilter.filterservice.krasnoyarskCompanyCorrectDto
import com.github.sawafrolov.loanrequestfilter.filterservice.krasnoyarskCompanyIncorrectDto
import com.github.sawafrolov.loanrequestfilter.filterservice.nonResidentDto

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
        val stopFactors = loanRequestValidationService.checkStopFactors(companyIncorrectDto())
        assertFalse(stopFactors.isEmpty())
        assertEquals(3, stopFactors.size)
        assertTrue(stopFactors.contains("Кредиты выдаются компаниям не более чем на 6 месяцев"))
        assertTrue(stopFactors.contains("Кредиты выдаются компаниям не более чем на 20 миллионов рублей"))
        assertTrue(stopFactors.contains("Капитал компании должен превышать 40 процентов от запрашиваемой суммы"))
    }

    @Test
    @DisplayName("Test human correct")
    fun testHumanCorrect() {
        assertTrue(loanRequestValidationService.checkStopFactors(humanCorrectDto()).isEmpty())
    }

    @Test
    @DisplayName("Test human border values")
    fun testHumanBorderValues() {
        assertTrue(loanRequestValidationService.checkStopFactors(humanBorderValuesDto()).isEmpty())
    }

    @Test
    @DisplayName("Test human incorrect")
    fun testHumanIncorrect() {
        val stopFactors = loanRequestValidationService.checkStopFactors(humanIncorrectDto())
        assertFalse(stopFactors.isEmpty())
        assertEquals(3, stopFactors.size)
        assertTrue(stopFactors.contains("Кредиты выдаются физическим лицам не более чем на 24 месяца"))
        assertTrue(stopFactors.contains("Кредиты выдаются физическим лицам не более чем на 2 миллиона рублей"))
        assertTrue(stopFactors.contains("Капитал физического лица должен превышать 10 процентов от запрашиваемой суммы"))
    }

    @Test
    @DisplayName("Test krasnoyarsk company correct")
    fun testKrasnoyarskCompanyCorrect() {
        assertTrue(loanRequestValidationService.checkStopFactors(krasnoyarskCompanyCorrectDto()).isEmpty())
    }

    @Test
    @DisplayName("Test krasnoyarsk company border values")
    fun testKrasnoyarskCompanyBorderValues() {
        assertTrue(loanRequestValidationService.checkStopFactors(krasnoyarskCompanyBorderValuesDto()).isEmpty())
    }

    @Test
    @DisplayName("Test krasnoyarsk company incorrect")
    fun testKrasnoyarskCompanyIncorrect() {
        val stopFactors = loanRequestValidationService.checkStopFactors(krasnoyarskCompanyIncorrectDto())
        assertFalse(stopFactors.isEmpty())
        assertEquals(1, stopFactors.size)
        assertTrue(stopFactors.contains("Капитал компании из Красноярского края должен быть больше 5 миллионов рублей"))
    }

    @Test
    @DisplayName("Test non-resident")
    fun testNonresident() {
        val stopFactors = loanRequestValidationService.checkStopFactors(nonResidentDto())
        assertFalse(stopFactors.isEmpty())
        assertEquals(1, stopFactors.size)
        assertTrue(stopFactors.contains("Кредиты не выдаются нерезидентам РФ"))
    }
}
