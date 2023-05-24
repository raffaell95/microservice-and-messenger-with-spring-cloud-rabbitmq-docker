package br.com.raffa.mscartoes.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
data class Cartao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,

    @Enumerated(EnumType.STRING)
    val bandeira: BandeiraCartao,
    val renda: BigDecimal,
    val limiteBasico: BigDecimal
)
