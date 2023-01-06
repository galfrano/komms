package com.chiliadmin.komms.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Listeners {
    @KafkaListener(topics = ["someTopic"], groupId = "someGroupId")
    fun listener(data: String){
        println("Listened to message: $data")
    }
}