package br.com.raffa.msclientes.application

import br.com.raffa.msclientes.application.representation.ClienteSaveRequest
import br.com.raffa.msclientes.domain.Cliente
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.Optional

@RestController
@RequestMapping("clientes")
class ClientesResource(private val service: ClienteService) {

//    @GetMapping
//    fun status(): String{
//        return "ok"
//    }

    @PostMapping
    fun save(@RequestBody request: ClienteSaveRequest): ResponseEntity<Cliente>{
        val cliente = request.toModel()
        service.save(cliente)
        val headerLocation = ServletUriComponentsBuilder
            .fromCurrentRequest().query("cpf={cpf}")
            .buildAndExpand(cliente.cpf).toUri()
        return ResponseEntity.created(headerLocation).build()
    }

    @GetMapping
    fun dadosCliente(@RequestParam("cpf") cpf: String): ResponseEntity<Optional<Cliente>>{
        val cliente = service.getByCPF(cpf)
        if(cliente.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(cliente)
    }
}