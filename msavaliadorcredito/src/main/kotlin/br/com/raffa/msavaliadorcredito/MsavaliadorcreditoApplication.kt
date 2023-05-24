package br.com.raffa.msavaliadorcredito

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableRabbit
class MsavaliadorcreditoApplication

fun main(args: Array<String>) {
	runApplication<MsavaliadorcreditoApplication>(*args)
}
