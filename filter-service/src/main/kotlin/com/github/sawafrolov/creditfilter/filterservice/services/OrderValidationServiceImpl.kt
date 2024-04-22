package com.github.sawafrolov.creditfilter.filterservice.services

import com.github.sawafrolov.creditfilter.filterservice.mappers.LoanRequestMapper
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
class OrderValidationServiceImpl(
    private val dmnEngine: DmnEngine,
    private val dmnDecision: DmnDecision,
    private val loanRequestMapper: LoanRequestMapper,
    private val orderCreateDto2VariableMapConverter: Converter<LoanRequestCheckDto, VariableMap>
): OrderValidationService {

    override fun checkStopFactors(loanRequestDto: LoanRequestDto): List<String> {
        val loanRequestCheckDto = loanRequestMapper.mapToCheckDto(loanRequestDto)
        val variables = orderCreateDto2VariableMapConverter.convert(loanRequestCheckDto)

        // todo спарсить результат из камунды
        val result = dmnEngine
            .evaluateDecisionTable(dmnDecision, variables)
            .resultList

        return listOf()
    }
}
