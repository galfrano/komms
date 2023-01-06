package com.chiliadmin.komms.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class Config {
    @Bean
    fun createTopic(): NewTopic{
        return TopicBuilder.name("someTopic").build()
    }
}