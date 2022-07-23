package com.fmmobile.tradewallet.stock.services

import com.fmmobile.tradewallet.stock.exception.ResourceNotFoundException
import com.fmmobile.tradewallet.stock.model.Stock
import com.fmmobile.tradewallet.stock.repositories.StockRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class StockService {
    private val logger = Logger.getLogger(StockService::class.java.getName())

    @Autowired
    lateinit var repository: StockRepository

    fun findAll(): List<Stock> {
        logger.info("Finding all stocks!")
        return repository.findAll()
    }

    fun findById(id: Long): Stock {
        logger.info("Finding one stock!")
        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
    }

    fun create(stock: Stock): Stock {
        logger.info("Creating stock!")
        return repository.save(stock)
    }

    fun update(stock: Stock): Stock {
        logger.info("Updating stock")
        val entity: Stock = repository.findById(stock.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        entity.average = stock.average
        entity.price = stock.price
        entity.name = stock.name
        entity.symbol = stock.symbol
        entity.updated = stock.updated
        entity.last_price = stock.last_price
        entity.minimum = stock.minimum
        entity.open = stock.open
        entity.variantion = stock.variantion
        entity.volum = stock.volum
        return repository.save(stock)
    }

    fun delete(id: Long) {
        logger.info("Deleting stock!")
        val entity: Stock = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}