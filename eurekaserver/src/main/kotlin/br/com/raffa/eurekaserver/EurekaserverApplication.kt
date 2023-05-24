package br.com.raffa.eurekaserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableEurekaServer
//@EnableWebSecurity
class EurekaserverApplication

fun main(args: Array<String>) {
	runApplication<EurekaserverApplication>(*args)
}
