package br.com.raffa.mscloudgateway.config

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RoutesConfig {

    @Bean
    fun routes(builder: RouteLocatorBuilder) = builder.routes {
        route {
            path("/clientes/**")
            uri("lb://msclientes")
        }
        route {
            path("/cartoes/**")
            uri("lb://mscartoes")
        }
        route {
            path("/avaliacoes-credito/**")
            uri("lb://msavaliadorcredito")
        }
    }
}