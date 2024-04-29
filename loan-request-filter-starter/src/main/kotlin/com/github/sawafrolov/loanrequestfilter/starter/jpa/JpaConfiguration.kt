package com.github.sawafrolov.loanrequestfilter.starter.jpa

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan("com.github.sawafrolov.loanrequestfilter.commons.entities")
class JpaConfiguration
