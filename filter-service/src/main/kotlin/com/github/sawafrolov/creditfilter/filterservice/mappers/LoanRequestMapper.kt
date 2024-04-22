package com.github.sawafrolov.creditfilter.filterservice.mappers

import com.github.sawafrolov.loanrequestfilter.commons.documents.LoanRequestDocument
import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestCheckDto
import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto
import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface LoanRequestMapper {

    fun mapToEntity(loanRequestDto: LoanRequestDto): LoanRequest

    fun mapToCheckDto(loanRequestDto: LoanRequestDto): LoanRequestCheckDto

    fun mapToDocument(loanRequestDto: LoanRequestDto): LoanRequestDocument
}
