package br.com.raffa.mscartoes.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class DadosSolicitacaoEmissaoCartao(

    @JsonProperty("idCartao")
    val idCartao: Long,

    @JsonProperty("cpf")
    val cpf: String,

    @JsonProperty("endereco")
    val endereco: String,

    @JsonProperty("limiteLiberado")
    val limiteLiberado: BigDecimal
)