package com.github.sawafrolov.loanrequestfilter.starter.elasticsearch

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import java.time.Duration

@Configuration
@EnableElasticsearchRepositories
@ConditionalOnProperty(
    prefix = "elasticsearch",
    name = ["enabled"],
    value = ["true"]
)
class ElasticSearchClientConfiguration: ElasticsearchConfiguration() {

    @Value("\${elasticsearch.url}")
    private lateinit var elasticSearchUrl: String

    @Value("\${elasticsearch.connection-timeout}")
    private lateinit var elasticSearchConnectionTimeout: String

    override fun clientConfiguration(): ClientConfiguration =
        ClientConfiguration
            .builder()
            .connectedTo(elasticSearchUrl)
            .withConnectTimeout(Duration.parse(elasticSearchConnectionTimeout))
            .build()
}
