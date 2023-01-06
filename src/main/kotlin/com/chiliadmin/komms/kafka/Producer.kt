package com.chiliadmin.komms.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class Producer(
        @Value("\${spring.kafka.bootstrap-servers}")
        val bootstrapServers: String
) {
    fun config(): Map<String, Any>{
        val props = HashMap<String, Any>()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return props
    }
    @Bean
    fun factory(): ProducerFactory<String, String>{
        return DefaultKafkaProducerFactory<String, String>(config())
    }
    @Bean
    fun template(
            factory: ProducerFactory<String, String>
    ): KafkaTemplate<String, String>{
        return KafkaTemplate(factory)
    }
}