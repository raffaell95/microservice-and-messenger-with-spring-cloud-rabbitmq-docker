package br.com.raffa.msavaliadorcredito.infra.mqueue

import br.com.raffa.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoCartao
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class SolicitacaoEmissaoCartaoPublisher(
    private val rabbitTemplate: RabbitTemplate,
    private val queueEmissaoCartoes: Queue
) {

    @Throws(JsonProcessingException::class)
    fun solicitarCartao(dados: DadosSolicitacaoEmissaoCartao){
        val json = convertIntoJson(dados)
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.name, json)
    }

    @Throws(JsonProcessingException::class)
    private fun convertIntoJson(dados: DadosSolicitacaoEmissaoCartao): String{
        val mapper = ObjectMapper()
        val json = mapper.writeValueAsString(dados)
        return json
    }

}