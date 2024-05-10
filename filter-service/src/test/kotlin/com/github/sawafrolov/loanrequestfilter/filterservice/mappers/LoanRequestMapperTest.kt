package com.github.sawafrolov.loanrequestfilter.filterservice.mappers

import com.github.sawafrolov.loanrequestfilter.filterservice.companyCorrectDto
import com.github.sawafrolov.loanrequestfilter.filterservice.loanRequestDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LoanRequestMapperTest(
    @Autowired val loanRequestMapper: LoanRequestMapper
) {

    @Test
    @DisplayName("Test loan request mapper")
    fun testMapper() {
        val result = loanRequestMapper.mapToCheckDto(loanRequestDto())
        assertEquals(companyCorrectDto(), result)
    }
}
