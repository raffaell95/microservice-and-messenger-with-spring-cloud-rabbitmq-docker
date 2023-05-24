package br.com.raffa.msavaliadorcredito.domain.model

import java.math.BigDecimal

data class Cartao (
    val id: Long,
    val nome: String,
    val bandeira: String,
    val limiteBasico: BigDecimal
)