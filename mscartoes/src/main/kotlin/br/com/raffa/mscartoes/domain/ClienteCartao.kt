package br.com.raffa.mscartoes.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
data class ClienteCartao(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val cpf: String,
    @ManyToOne
    @JoinColumn(name = "id_cartao")
    val cartao: Cartao,
    val limiteLiberado: BigDecimal
)