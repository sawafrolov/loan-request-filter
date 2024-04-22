package com.github.sawafrolov.loanrequestfilter.filterservice.configuration

import org.camunda.bpm.dmn.engine.DmnDecision
import org.camunda.bpm.dmn.engine.DmnEngine
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class CamundaConfiguration {

    @Value("\${camunda.dmn-file-location}")
    private lateinit var dmnFileLocation: String

    private val dmnDecisionKey = "Decision_0ozg60u"

    @Bean
    fun dmnEngine(): DmnEngine {
        return DmnEngineConfiguration
            .createDefaultDmnEngineConfiguration()
            .buildEngine()
    }

    @Bean
    fun dmnDecision(dmnEngine: DmnEngine): DmnDecision {
        FileInputStream(dmnFileLocation).use {
            return dmnEngine.parseDecision(dmnDecisionKey, it)
        }
    }
}
