package br.com.raffa.mscartoes.application

import br.com.raffa.mscartoes.domain.ClienteCartao
import br.com.raffa.mscartoes.infra.repository.ClienteCartaoRepository
import org.springframework.stereotype.Service

@Service
class ClienteCartaoService(private val repository: ClienteCartaoRepository) {

    fun listarCartoesPorCpf(cpf: String): List<ClienteCartao>{
        return repository.findByCpf(cpf)
    }

}