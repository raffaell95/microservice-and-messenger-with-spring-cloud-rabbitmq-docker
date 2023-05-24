package br.com.raffa.msavaliadorcredito.domain.model

import java.math.BigDecimal

data class DadosSolicitacaoEmissaoCartao(
    val idCartao: Long,
    val cpf: String,
    val endereco: String,
    val limiteLiberado: BigDecimal?
)