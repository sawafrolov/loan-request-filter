package com.github.sawafrolov.loanrequestfilter.filterservice.handlers

import com.github.sawafrolov.loanrequestfilter.commons.enums.LoanRequestStatus
import com.github.sawafrolov.loanrequestfilter.filterservice.companyCorrectDto
import com.github.sawafrolov.loanrequestfilter.filterservice.loanRequestCorrect
import com.github.sawafrolov.loanrequestfilter.filterservice.loanRequestCorrectDto
import com.github.sawafrolov.loanrequestfilter.filterservice.loanRequestIncorrect
import com.github.sawafrolov.loanrequestfilter.filterservice.loanRequestIncorrectDto
import com.github.sawafrolov.loanrequestfilter.filterservice.mappers.LoanRequestMapper
import com.github.sawafrolov.loanrequestfilter.filterservice.nonResidentDto
import com.github.sawafrolov.loanrequestfilter.filterservice.services.LoanRequestValidationService
import com.github.sawafrolov.loanrequestfilter.starter.jpa.repositories.LoanRequestRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
class LoanRequestKafkaHandlerTest(
    val loanRequestMapper: LoanRequestMapper = mockk(),
    val loanRequestRepository: LoanRequestRepository = mockk(),
    val loanRequestValidationService: LoanRequestValidationService = mockk()
) {

    private val loanRequestKafkaHandler: LoanRequestKafkaHandler = LoanRequestKafkaHandler(
        loanRequestMapper,
        loanRequestRepository,
        loanRequestValidationService
    )

    @Test
    @DisplayName("Test draft loan request")
    fun testDraft() {
        val loanRequestDto = loanRequestCorrectDto(UUID.randomUUID())
        loanRequestDto.status = LoanRequestStatus.DRAFT
        assertThrows<IllegalArgumentException> {
            loanRequestKafkaHandler.handleLoanRequest(loanRequestDto)
        }
    }

    @Test
    @DisplayName("Test correct loan request")
    fun testCorrect() {
        val uuid = UUID.randomUUID()
        val loanRequest = loanRequestCorrect(uuid)
        val loanRequestDto = loanRequestCorrectDto(uuid)

        every { loanRequestMapper.mapToEntity(loanRequestDto) } answers { callOriginal() }
        every { loanRequestMapper.mapToCheckDto(loanRequestDto) } answers { callOriginal() }
        every { loanRequestValidationService.checkStopFactors(companyCorrectDto()) } answers { callOriginal() }

        loanRequest.stopFactors = null
        loanRequest.status = LoanRequestStatus.IN_PROGRESS
        loanRequest.protectedFromChange = true

        var invCount = 0
        every { loanRequestRepository.save(loanRequest) } answers {
            invCount += 1
            loanRequest
        }

        assertEquals(1, invCount)
    }

    @Test
    @DisplayName("Test incorrect loan request")
    fun testIncorrect() {
        val uuid = UUID.randomUUID()
        val loanRequest = loanRequestIncorrect(uuid)
        val loanRequestDto = loanRequestIncorrectDto(uuid)

        every { loanRequestMapper.mapToEntity(loanRequestDto) } answers { callOriginal() }
        every { loanRequestMapper.mapToCheckDto(loanRequestDto) } answers { callOriginal() }
        every { loanRequestValidationService.checkStopFactors(nonResidentDto()) } answers { callOriginal() }

        loanRequest.stopFactors = "Кредиты не выдаются нерезидентам РФ"
        loanRequest.status = LoanRequestStatus.STOP_FACTORS

        var invCount = 0
        every { loanRequestRepository.save(loanRequest) } answers {
            invCount += 1
            loanRequest
        }

        assertEquals(1, invCount)
    }
}
