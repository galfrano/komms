package com.chiliadmin.komms

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.kafka.core.KafkaTemplate
import java.text.SimpleDateFormat
import java.util.*

@SpringBootApplication
class KommsApplication(
		@Autowired
		private val template: KafkaTemplate<String, String>
){
	init {
		val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
		val date = sdf.format(Date())
		template.send("someTopic", "da message with time $date")
		println("done sendin")
	}
	companion object{
		@JvmStatic
		fun main(args: Array<String>){
			SpringApplication.run(KommsApplication::class.java, *args)
		}

	}
}
