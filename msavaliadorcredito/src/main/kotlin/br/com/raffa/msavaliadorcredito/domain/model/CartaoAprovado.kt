package br.com.raffa.msavaliadorcredito.domain.model

import java.math.BigDecimal

data class CartaoAprovado (
    val cartao: String,
    val bandeira: String,
    val limiteAprovado: BigDecimal
)