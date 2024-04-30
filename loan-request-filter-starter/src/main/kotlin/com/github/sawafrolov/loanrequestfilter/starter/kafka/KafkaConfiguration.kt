package com.github.sawafrolov.loanrequestfilter.starter.kafka

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka

@Configuration
@EnableKafka
@ConditionalOnProperty(
    prefix = "kafka",
    name = ["enabled"],
    havingValue = "true"
)
class KafkaConfiguration
