package br.com.raffa.msavaliadorcredito.application

import br.com.raffa.msavaliadorcredito.application.ex.DadosCLienteNotFoundException
import br.com.raffa.msavaliadorcredito.application.ex.ErroSolicitacaoCartaoException
import br.com.raffa.msavaliadorcredito.application.ex.ErrorComunicacaoMicroservicesException
import br.com.raffa.msavaliadorcredito.domain.model.*
import br.com.raffa.msavaliadorcredito.infra.clients.CartoesResourceClient
import br.com.raffa.msavaliadorcredito.infra.clients.ClienteResourceClient
import br.com.raffa.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.BigInteger
import java.util.UUID
import java.util.stream.Collectors
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@Service
class AvaliadorCreditoService(
    private val clientesClient: ClienteResourceClient,
    private val cartoesCliente: CartoesResourceClient,
    private val emissaoCartaoPublisher: SolicitacaoEmissaoCartaoPublisher) {

    fun obterSituacaoCliente(cpf: String): SituacaoCliente{
        val dadosClienteResponse = clientesClient.dadosCliente(cpf)
        val cartoesResposne = cartoesCliente.getCartoesByCliente(cpf)

        try {
            return SituacaoCliente(cliente = dadosClienteResponse.body?.get(),
                cartoes = cartoesResposne.body)
        }catch (e: FeignException.FeignClientException){
            val status = e.status()
            if (HttpStatus.NOT_FOUND.value() == status){
                throw DadosCLienteNotFoundException()
            }
            throw ErrorComunicacaoMicroservicesException(e.message.toString(), e.status())
        }
    }

    fun realizarAvaliacao(cpf: String, renda: Long): RetornoAvaliacaoCliente {
        try {
            val dadosClienteResponse = clientesClient.dadosCliente(cpf)
            val cartoesResponse = cartoesCliente.getCartoesRendaAteh(renda)

            val cartoes = cartoesResponse.body

            val listaCartoesAprovados = cartoes?.stream()?.map { cartao ->

                val dadosCliente = dadosClienteResponse.body?.get()

                val limiteBasico = cartao.limiteBasico
                val idadeBD = BigDecimal.valueOf(dadosCliente?.idade as Double)
                val fator = idadeBD.divide(BigDecimal.valueOf(10))
                val limiteAprovado = fator.multiply(limiteBasico)

                CartaoAprovado(
                    cartao = cartao.nome,
                    bandeira = cartao.bandeira,
                    limiteAprovado = limiteAprovado
                )
            }?.collect(Collectors.toList())

            return RetornoAvaliacaoCliente(listaCartoesAprovados!!.toList())

        }catch (e: FeignException.FeignClientException){
            val status = e.status()
            if (HttpStatus.NOT_FOUND.value() == status){
                throw DadosCLienteNotFoundException()
            }
            throw ErrorComunicacaoMicroservicesException(e.message.toString(), e.status())
        }
    }

    fun solicitarEmissaoCartao(dados: DadosSolicitacaoEmissaoCartao): ProtocoloSolicitacaoCartao {
        try {
            emissaoCartaoPublisher.solicitarCartao(dados)
            val protocolo = UUID.randomUUID().toString()
            return ProtocoloSolicitacaoCartao(protocolo)
        }catch (e: Exception){
            throw ErroSolicitacaoCartaoException(e.message.toString())
        }
    }

}

