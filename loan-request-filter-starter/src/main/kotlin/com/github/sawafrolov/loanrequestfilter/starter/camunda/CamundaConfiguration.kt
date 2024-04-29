package com.github.sawafrolov.loanrequestfilter.starter.camunda

import org.camunda.bpm.dmn.engine.DmnDecision
import org.camunda.bpm.dmn.engine.DmnEngine
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
@ConditionalOnProperty(
    prefix = "camunda",
    name = ["enabled"],
    value = ["true"]
)
class CamundaConfiguration {

    @Value("\${camunda.dmn.file-location}")
    private lateinit var dmnFileLocation: String

    @Value("\${camunda.dmn.decision-key}")
    private lateinit var dmnDecisionKey: String

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
