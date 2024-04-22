package com.github.sawafrolov.loanrequestfilter.loanrequestservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoanRequestServiceApplication

fun main(args: Array<String>) {
    runApplication<LoanRequestServiceApplication>(*args)
}
