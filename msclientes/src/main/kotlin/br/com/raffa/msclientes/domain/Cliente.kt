package br.com.raffa.msclientes.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: Long? = null,
     val cpf: String,
     val nome: String,
     val idade: Int
)