package com.github.sawafrolov.loanrequestfilter.loanrequestservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("com.github.sawafrolov.loanrequestfilter.commons.entities")
class LoanRequestServiceApplication

fun main(args: Array<String>) {
    runApplication<LoanRequestServiceApplication>(*args)
}
