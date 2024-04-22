package com.github.sawafrolov.creditfilter.filterservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class FilterServiceApplication

fun main(args: Array<String>) {
    runApplication<FilterServiceApplication>(*args)
}
