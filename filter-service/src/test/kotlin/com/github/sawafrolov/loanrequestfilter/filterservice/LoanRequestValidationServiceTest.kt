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
    @DisplayName("Проверка на корректном тестовом примере")
    fun testCorrect() {
        assertTrue(loanRequestValidationService.checkStopFactors(correctDto()).isEmpty())
    }

    @Test
    @DisplayName("Проверка на тестовом примере для ИП")
    fun testIp() {
        val result = loanRequestValidationService.checkStopFactors(ipDto())
        assertFalse(result.isEmpty())
        assertEquals(1, result.size)
        assertEquals("Не выдаем кредиты ИП", result[0])
    }

    @Test
    @DisplayName("Проверка на тестовом примере для Красноярского края")
    fun testKrasnoyarsk() {
        val result = loanRequestValidationService.checkStopFactors(krasnoyarskDto())
        assertFalse(result.isEmpty())
        assertEquals(1, result.size)
        assertEquals("Не выдаем кредиты компаниям из Красноярского края", result[0])
    }
}
