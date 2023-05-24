package br.com.raffa.msavaliadorcredito.infra.clients

import br.com.raffa.msavaliadorcredito.domain.model.DadosCliente
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(value = "msclientes", path = "/clientes")
interface ClienteResourceClient {

    @GetMapping
    fun dadosCliente(@RequestParam("cpf") cpf: String): ResponseEntity<Optional<DadosCliente>>

}