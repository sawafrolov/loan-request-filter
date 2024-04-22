package com.github.sawafrolov.loanrequestfilter.filterservice.converters

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.sawafrolov.loanrequestfilter.commons.dto.LoanRequestCheckDto
import org.camunda.bpm.engine.variable.VariableMap
import org.camunda.bpm.engine.variable.Variables
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class LoanRequestCheckDto2VariableMapConverter: Converter<LoanRequestCheckDto, VariableMap> {

    override fun convert(source: LoanRequestCheckDto): VariableMap? {
        return Variables.fromMap(
            jacksonObjectMapper()
                .convertValue(source, Map::class.java)
                    as Map<String, Any>
        )
    }
}
