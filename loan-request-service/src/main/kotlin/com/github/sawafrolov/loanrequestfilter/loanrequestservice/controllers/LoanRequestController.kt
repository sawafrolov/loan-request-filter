package com.github.sawafrolov.loanrequestfilter.loanrequestservice.controllers

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestRejectDto
import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import com.github.sawafrolov.loanrequestfilter.loanrequestservice.services.LoanRequestService
import lombok.RequiredArgsConstructor
import org.springframework.data.rest.webmvc.BasePathAwareController
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.hateoas.server.ExposesResourceFor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.UUID

@RequiredArgsConstructor
@BasePathAwareController
@RepositoryRestController
@ExposesResourceFor(LoanRequest::class)
class LoanRequestController(
    private val loanRequestService: LoanRequestService
) {

    @PutMapping(value = ["/loan-requests/submit/{loanRequestId}"])
    @ResponseStatus(HttpStatus.OK)
    fun submitLoanRequest(@PathVariable loanRequestId: UUID) {
        loanRequestService.submitLoanRequest(loanRequestId)
    }

    @PutMapping(value = ["/loan-requests/accept/{loanRequestId}"])
    @ResponseStatus(HttpStatus.OK)
    fun acceptLoanRequest(@PathVariable loanRequestId: UUID) {
        loanRequestService.acceptLoanRequest(loanRequestId)
    }

    @PutMapping(value = ["/loan-requests/reject/{loanRequestId}"])
    @ResponseStatus(HttpStatus.OK)
    fun rejectLoanRequest(@PathVariable loanRequestId: UUID, @RequestBody rejectDto: LoanRequestRejectDto) {
        loanRequestService.rejectLoanRequest(loanRequestId, rejectDto.rejectReason)
    }
}
