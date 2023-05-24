package br.com.raffa.mscartoes.infra.repository

import br.com.raffa.mscartoes.domain.ClienteCartao
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteCartaoRepository: JpaRepository<ClienteCartao, Long> {
    fun findByCpf(cpf: String): List<ClienteCartao>
}