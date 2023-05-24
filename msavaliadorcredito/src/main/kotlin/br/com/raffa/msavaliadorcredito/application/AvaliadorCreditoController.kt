package br.com.raffa.msavaliadorcredito.application

import br.com.raffa.msavaliadorcredito.application.ex.DadosCLienteNotFoundException
import br.com.raffa.msavaliadorcredito.application.ex.ErroSolicitacaoCartaoException
import br.com.raffa.msavaliadorcredito.application.ex.ErrorComunicacaoMicroservicesException
import br.com.raffa.msavaliadorcredito.domain.model.DadosAvaliacao
import br.com.raffa.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoCartao
import br.com.raffa.msavaliadorcredito.domain.model.ProtocoloSolicitacaoCartao
import br.com.raffa.msavaliadorcredito.domain.model.SituacaoCliente
import feign.Response
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("avaliacoes-credito")
class AvaliadorCreditoController(private val avaliadorCreditoService: AvaliadorCreditoService) {

    @GetMapping
    fun status(): String{
        return "Ok"
    }

    @GetMapping("situacao-cliente", params = ["cpf"])
    fun consultarSituacaoCliente(@RequestParam("cpf") cpf: String): ResponseEntity<out Any>? {
        try {
            val situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf)
            return ResponseEntity.ok(situacaoCliente)
        }catch (e: DadosCLienteNotFoundException){
            return ResponseEntity.notFound().build()
        }catch (e: ErrorComunicacaoMicroservicesException){
            return HttpStatus.resolve(e.status)?.let {
                ResponseEntity.status(it).body(e.message.toString())
            }
        }
    }

    @PostMapping
    fun realizarAvaliacao(@RequestBody dados: DadosAvaliacao): ResponseEntity<out Any>? {
        try {
            val retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados.cpf, dados.renda)
            return ResponseEntity.ok(retornoAvaliacaoCliente)
        }catch (e: DadosCLienteNotFoundException){
            return ResponseEntity.notFound().build()
        }catch (e: ErrorComunicacaoMicroservicesException){
            return HttpStatus.resolve(e.status)?.let {
                ResponseEntity.status(it).body(e.message.toString())
            }
        }
    }

    @PostMapping("solicitacoes-cartao")
    fun solicitarCartao(@RequestBody dados: DadosSolicitacaoEmissaoCartao): ResponseEntity<Any>{
        try {
            val protocoloSolicitacaoCartao = avaliadorCreditoService.solicitarEmissaoCartao(dados)
            return ResponseEntity.ok(protocoloSolicitacaoCartao)
        }catch (e: ErroSolicitacaoCartaoException){
            return ResponseEntity.internalServerError().body(e.message)
        }
    }

}