package com.chiliadmin.komms.api

import com.chiliadmin.komms.kafka.MessageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/")
class Controller (
        val template: KafkaTemplate<String, String>
){
    @PostMapping
    fun publish(@RequestBody request: MessageRequest){
        template.send("someTopic", request.message)
    }

    @GetMapping
    fun something(): ResponseEntity<*>? {
        template.send("someTopic", "GET on /api/ !!")
        return ResponseEntity<Any?>("hello", HttpStatus.OK)
    }
}