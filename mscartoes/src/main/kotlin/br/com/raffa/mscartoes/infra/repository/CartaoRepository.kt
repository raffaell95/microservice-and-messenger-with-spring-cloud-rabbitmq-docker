package br.com.raffa.mscartoes.infra.repository

import br.com.raffa.mscartoes.domain.Cartao
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal

interface CartaoRepository: JpaRepository<Cartao, Long>{
    fun findByRendaLessThanEqual(rendaBigDecimal: BigDecimal): List<Cartao>
}