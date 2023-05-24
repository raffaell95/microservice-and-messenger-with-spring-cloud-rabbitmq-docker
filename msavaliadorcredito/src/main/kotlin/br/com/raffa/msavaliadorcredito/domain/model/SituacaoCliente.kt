package br.com.raffa.msavaliadorcredito.domain.model

data class SituacaoCliente(
    val cliente: DadosCliente?,
    val cartoes: List<CartaoCliente>?
)
