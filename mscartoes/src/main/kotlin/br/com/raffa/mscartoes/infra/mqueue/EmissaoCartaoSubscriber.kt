package br.com.raffa.mscartoes.infra.mqueue

import br.com.raffa.mscartoes.domain.ClienteCartao
import br.com.raffa.mscartoes.domain.DadosSolicitacaoEmissaoCartao
import br.com.raffa.mscartoes.infra.repository.CartaoRepository
import br.com.raffa.mscartoes.infra.repository.ClienteCartaoRepository
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class EmissaoCartaoSubscriber(
    private val cartaoRepository: CartaoRepository,
    private val clienteCartaoRepository: ClienteCartaoRepository
) {

    @RabbitListener(queues = ["\${mq.queues.emissao-cartoes}"])
    fun receberSolicitacaoEmissao(@Payload payload: String){
        try {
            val mapper = ObjectMapper()
            val dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao::class.java)
            val cartao = cartaoRepository.findById(dados.idCartao).orElseThrow()
            val clienteCartao = ClienteCartao(
                cartao = cartao,
                cpf = dados.cpf,
                limiteLiberado = dados.limiteLiberado
            )

            clienteCartaoRepository.save(clienteCartao)

        }catch (e: JsonProcessingException){
            e.printStackTrace()
//            logger.error("Erro ao receber solicitacao de emissao de cartao: ${e.message}")
        }

    }

}