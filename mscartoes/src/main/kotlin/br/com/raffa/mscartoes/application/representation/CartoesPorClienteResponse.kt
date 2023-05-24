package br.com.raffa.mscartoes.application.representation

import br.com.raffa.mscartoes.domain.ClienteCartao
import java.math.BigDecimal

data class CartoesPorClienteResponse (
    val nome: String,
    val bandeira: String,
    val limiteLiberado: BigDecimal
) {
    companion object{
        fun fromModel(model: ClienteCartao) = CartoesPorClienteResponse(
            model.cartao.nome,
            model.cartao.bandeira.toString(),
            model.limiteLiberado
        )

    }
}
