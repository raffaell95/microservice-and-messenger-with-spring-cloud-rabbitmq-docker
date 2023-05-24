package br.com.raffa.msclientes.application.representation

import br.com.raffa.msclientes.domain.Cliente

data class ClienteSaveRequest(
     val cpf: String,
     val nome: String,
     val idade: Int
){
    fun toModel(): Cliente{
        return Cliente(cpf=cpf, nome = nome, idade = idade)
    }
}
