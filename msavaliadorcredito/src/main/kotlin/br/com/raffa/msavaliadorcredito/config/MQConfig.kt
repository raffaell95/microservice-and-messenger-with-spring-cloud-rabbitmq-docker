package br.com.raffa.msavaliadorcredito.config

import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MQConfig {

    @Value("\${mq.queues.emissao-cartoes}")
    private lateinit var emissaoCartoesFila: String

    @Bean
    fun queueEmissaoCartoes(): Queue{
         return Queue(emissaoCartoesFila, true)
    }
}