package com.fmmobile.tradewallet.stock.repositories

import com.fmmobile.tradewallet.stock.model.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional


@Repository
@Transactional
public interface StockRepository : JpaRepository<Stock, Long> {
    fun findBySymbol(symbol: String): Optional<List<Stock>>
    fun findBySemaphore(semaphore: Boolean): Optional<List<Stock>>

    @Modifying
    @Query("update Stock s set s.semaphore = ?2 where s.semaphore = ?1")
    fun updateSemaphoreStatus(from: Boolean, to: Boolean): Int
}