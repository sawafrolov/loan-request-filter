package com.github.sawafrolov.loanrequestfilter.filterservice.services

import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestCheckDto
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
    private val loanRequestCheckDto2VariableMapConverter: Converter<LoanRequestCheckDto, VariableMap>
): LoanRequestValidationService {

    override fun checkStopFactors(loanRequestCheckDto: LoanRequestCheckDto): List<String> {
        val variables = loanRequestCheckDto2VariableMapConverter.convert(loanRequestCheckDto)
        return dmnEngine
            .evaluateDecisionTable(dmnDecision, variables)
            .resultList
            .map { it["rejectReason"] as String }
    }
}
