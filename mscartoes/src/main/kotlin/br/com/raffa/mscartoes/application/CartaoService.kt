package br.com.raffa.mscartoes.application

import br.com.raffa.mscartoes.domain.Cartao
import br.com.raffa.mscartoes.infra.repository.CartaoRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.math.BigDecimal

@Service
class CartaoService(private val repository: CartaoRepository) {

    @Transactional
    fun save(cartao: Cartao): Cartao{
        return repository.save(cartao)
    }

    fun getCartoesRendaMenorIgual(renda: Long): List<Cartao>{
        val rendaBigDecimal = BigDecimal.valueOf(renda)
        return repository.findByRendaLessThanEqual(rendaBigDecimal)
    }
}