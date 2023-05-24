package br.com.raffa.msclientes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class MsclientesApplication

fun main(args: Array<String>) {
	runApplication<MsclientesApplication>(*args)
}
