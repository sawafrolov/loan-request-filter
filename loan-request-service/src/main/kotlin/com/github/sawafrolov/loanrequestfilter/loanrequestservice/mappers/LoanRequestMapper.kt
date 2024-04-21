package com.github.sawafrolov.loanrequestfilter.loanrequestservice.mappers

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto
import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface LoanRequestMapper {

    fun mapToDto(loanRequest: LoanRequest): LoanRequestDto
}
