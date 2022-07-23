package com.fmmobile.tradewallet.stock.repositories

import com.fmmobile.tradewallet.stock.model.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
public interface StockRepository : JpaRepository<Stock, Long> {}