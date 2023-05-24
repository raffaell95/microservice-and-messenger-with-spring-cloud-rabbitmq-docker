package br.com.raffa.msclientes.infra.repository

import br.com.raffa.msclientes.domain.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ClienteRepository: JpaRepository<Cliente, Long> {
    fun findByCpf(cpf: String): Optional<Cliente>
}