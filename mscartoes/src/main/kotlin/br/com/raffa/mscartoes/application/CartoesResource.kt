package br.com.raffa.mscartoes.application

import br.com.raffa.mscartoes.application.representation.CartaoSaveRequest
import br.com.raffa.mscartoes.application.representation.CartoesPorClienteResponse
import br.com.raffa.mscartoes.domain.Cartao
import br.com.raffa.mscartoes.domain.ClienteCartao
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors


@RestController
@RequestMapping("cartoes")
class CartoesResource(
    private val cartaoService: CartaoService,
    private val  clienteCartaoService: ClienteCartaoService) {

    @GetMapping
    fun status(): String{
        return "ok"
    }

    @PostMapping
    fun cadastra(@RequestBody request: CartaoSaveRequest): ResponseEntity<HttpStatus>{
        val cartao = request.toModel()
        cartaoService.save(cartao)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping(params = ["renda"])
    fun getCartoesRendaAteh(@RequestParam("renda") renda: Long): ResponseEntity<List<Cartao>>{
        val list = cartaoService.getCartoesRendaMenorIgual(renda)
        return ResponseEntity.ok(list)
    }

    @GetMapping(params = ["cpf"])
    fun getCartoesByCliente(@RequestParam("cpf") cpf: String): ResponseEntity<List<CartoesPorClienteResponse>>{
        val lista = clienteCartaoService.listarCartoesPorCpf(cpf)
        val resultList = lista.stream().map { CartoesPorClienteResponse.fromModel(it) }
            .collect(Collectors.toList())
        return ResponseEntity.ok(resultList)
    }

}