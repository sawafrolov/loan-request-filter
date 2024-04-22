package com.github.sawafrolov.loanrequestfilter.filterservice.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@Configuration
@EntityScan("com.github.sawafrolov.loanrequestfilter.commons.entities")
@EnableElasticsearchRepositories
class ElasticSearchClientConfiguration: ElasticsearchConfiguration() {

    override fun clientConfiguration(): ClientConfiguration {
        return ClientConfiguration
            .builder()
            .connectedTo("localhost:9200")
            .build()
    }
}
