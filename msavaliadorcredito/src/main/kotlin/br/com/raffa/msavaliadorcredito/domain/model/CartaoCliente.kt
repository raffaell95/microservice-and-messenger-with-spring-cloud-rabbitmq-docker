package br.com.raffa.msavaliadorcredito.domain.model

import java.math.BigDecimal

data class CartaoCliente (
    val nome: String,
    val bandeira: String,
    val limiteLiberado: BigDecimal
)
