package com.github.sawafrolov.loanrequestfilter.filterservice.configuration

import org.camunda.bpm.dmn.engine.DmnDecision
import org.camunda.bpm.dmn.engine.DmnEngine
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class CamundaConfiguration {

    private val dmnFilePath = "filter-service/src/main/resources/camunda/diagram.dmn"

    private val dmnDecisionKey = "Decision_0ozg60u"

    @Bean
    fun dmnEngine(): DmnEngine {
        return DmnEngineConfiguration
            .createDefaultDmnEngineConfiguration()
            .buildEngine()
    }

    @Bean
    fun dmnDecision(dmnEngine: DmnEngine): DmnDecision {
        FileInputStream(dmnFilePath).use {
            return dmnEngine.parseDecision(dmnDecisionKey, it)
        }
    }
}
