package br.com.raffa.msavaliadorcredito.infra.clients

import br.com.raffa.msavaliadorcredito.domain.model.Cartao
import br.com.raffa.msavaliadorcredito.domain.model.CartaoCliente
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("mscartoes", path = "/cartoes")
interface CartoesResourceClient {

    @GetMapping(params = ["cpf"])
    fun getCartoesByCliente(@RequestParam("cpf") cpf: String): ResponseEntity<List<CartaoCliente>>

    @GetMapping(params = ["renda"])
    fun getCartoesRendaAteh(@RequestParam("renda") renda: Long): ResponseEntity<List<Cartao>>

}