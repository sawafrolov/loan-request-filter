package com.github.sawafrolov.loanrequestfilter.filterservice.services

import com.github.sawafrolov.loanrequestfilter.filterservice.mappers.LoanRequestMapper
import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestCheckDto
import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestDto
import lombok.RequiredArgsConstructor
import org.camunda.bpm.dmn.engine.DmnDecision
import org.camunda.bpm.dmn.engine.DmnEngine
import org.camunda.bpm.engine.variable.VariableMap
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class LoanRequestValidationServiceImpl(
    private val dmnEngine: DmnEngine,
    private val dmnDecision: DmnDecision,
    private val loanRequestMapper: LoanRequestMapper,
    private val loanRequestCheckDto2VariableMapConverter: Converter<LoanRequestCheckDto, VariableMap>
): LoanRequestValidationService {

    override fun checkStopFactors(loanRequestDto: LoanRequestDto): List<String> {
        val loanRequestCheckDto = loanRequestMapper.mapToCheckDto(loanRequestDto)
        val variables = loanRequestCheckDto2VariableMapConverter.convert(loanRequestCheckDto)

        return dmnEngine
            .evaluateDecisionTable(dmnDecision, variables)
            .resultList
            .map { it["rejectReason"] as String }
    }
}
