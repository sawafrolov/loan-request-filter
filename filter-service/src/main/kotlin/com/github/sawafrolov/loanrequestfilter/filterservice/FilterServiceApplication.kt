package com.github.sawafrolov.loanrequestfilter.filterservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
@EntityScan("com.github.sawafrolov.loanrequestfilter.commons.entities")
class FilterServiceApplication

fun main(args: Array<String>) {
    runApplication<FilterServiceApplication>(*args)
}
