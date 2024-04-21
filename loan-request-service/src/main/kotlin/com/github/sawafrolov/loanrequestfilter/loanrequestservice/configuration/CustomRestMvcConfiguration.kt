package com.github.sawafrolov.loanrequestfilter.loanrequestservice.configuration

import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry

@Configuration
@EntityScan("com.github.sawafrolov.loanrequestfilter.commons.entities")
class CustomRestMvcConfiguration {

    @Bean
    fun repositoryRestConfigurer(): RepositoryRestConfigurer {
        return object : RepositoryRestConfigurer {
            override fun configureRepositoryRestConfiguration(
                restConfig: RepositoryRestConfiguration,
                cors: CorsRegistry
            ) {
                restConfig.exposeIdsFor(LoanRequest::class.java)
            }
        }
    }
}
