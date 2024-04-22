package com.github.sawafrolov.creditfilter

import com.github.sawafrolov.creditfilter.filterservice.services.OrderValidationService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LoanRequestValidationServiceTest(
    @Autowired val orderValidationService: OrderValidationService
) {

    @Test
    @DisplayName("Проверка на корректном тестовом примере")
    fun testCorrect() {
        assertTrue(orderValidationService.checkStopFactors(correctDto()).isEmpty())
    }

    @Test
    @DisplayName("Проверка на тестовом примере для ИП")
    fun testIp() {
        val result = orderValidationService.checkStopFactors(ipDto())
        assertFalse(result.isEmpty())
        assertEquals(1, result.size)
        assertEquals("Не выдаем кредиты ИП", result[0])
    }

    @Test
    @DisplayName("Проверка на тестовом примере для Красноярского края")
    fun testKrasnoyarsk() {
        val result = orderValidationService.checkStopFactors(krasnoyarskDto())
        assertFalse(result.isEmpty())
        assertEquals(1, result.size)
        assertEquals("Не выдаем кредиты компаниям из Красноярского края", result[0])
    }
}
