package br.com.raffa.mscloudgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.server.router

@SpringBootApplication
class MscloudgatewayApplication

fun main(args: Array<String>) {
	runApplication<MscloudgatewayApplication>(*args)
}
