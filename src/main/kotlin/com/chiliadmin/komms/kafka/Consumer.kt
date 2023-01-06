package com.chiliadmin.komms.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer

@Configuration
class Consumer (
        @Value("\${spring.kafka.bootstrap-servers}")
        val bootstrapServers: String
){
    fun config(): Map<String, Any>{
        val props = HashMap<String, Any>()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return props
    }
    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        return DefaultKafkaConsumerFactory(config())
    }

    fun listener(): KafkaListenerContainerFactory<
            ConcurrentMessageListenerContainer<String, String>>{
        val factory: ConcurrentKafkaListenerContainerFactory<String, String> = ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}