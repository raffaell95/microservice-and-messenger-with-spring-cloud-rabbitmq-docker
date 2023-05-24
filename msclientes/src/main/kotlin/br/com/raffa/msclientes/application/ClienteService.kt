package br.com.raffa.msclientes.application

import br.com.raffa.msclientes.domain.Cliente
import br.com.raffa.msclientes.infra.repository.ClienteRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class ClienteService(private val repository: ClienteRepository) {

    fun save(cliente: Cliente): Cliente{
        return repository.save(cliente)
    }

    fun getByCPF(cpf: String): Optional<Cliente>{
        return repository.findByCpf(cpf)
    }
}