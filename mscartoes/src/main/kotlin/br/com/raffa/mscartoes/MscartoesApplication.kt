package br.com.raffa.mscartoes

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableRabbit
class MscartoesApplication

fun main(args: Array<String>) {
	runApplication<MscartoesApplication>(*args)
}
