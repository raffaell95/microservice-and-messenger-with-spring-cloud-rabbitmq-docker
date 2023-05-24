package br.com.raffa.msavaliadorcredito.application.ex

class ErrorComunicacaoMicroservicesException(msg: String, val status: Int): Exception(msg)