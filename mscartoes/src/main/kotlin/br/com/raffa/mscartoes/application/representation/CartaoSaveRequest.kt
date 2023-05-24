package br.com.raffa.mscartoes.application.representation

import br.com.raffa.mscartoes.domain.BandeiraCartao
import br.com.raffa.mscartoes.domain.Cartao
import java.math.BigDecimal

data class CartaoSaveRequest (
    val nome: String,
    val bandeira: BandeiraCartao,
    val renda: BigDecimal,
    val limite: BigDecimal
){
    fun toModel() = Cartao(
        nome=nome,
        bandeira = bandeira,
        renda = renda,
        limiteBasico = limite
    )
}
