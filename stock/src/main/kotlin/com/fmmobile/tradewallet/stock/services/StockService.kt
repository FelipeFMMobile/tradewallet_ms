package com.fmmobile.tradewallet.stock.services

import com.fmmobile.tradewallet.stock.data.vo.v1.StockVO
import com.fmmobile.tradewallet.stock.exception.ResourceNotFoundException
import com.fmmobile.tradewallet.stock.mapper.DozerMapper
import com.fmmobile.tradewallet.stock.mapper.DozerMapper.Companion.parseListObject
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

    fun findAll(): List<StockVO> {
        return parseListObject(repository.findAll() as List<Stock>, StockVO::class.java)
    }

    fun findById(id: Long): StockVO {
        logger.info("Finding one stock!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        return DozerMapper.parseObject(entity, StockVO::class.java)
    }

    fun findBySymbol(symbol: String): StockVO {
        logger.info("Finding one stock!")
        val entity = repository.findBySymbol(symbol)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        return DozerMapper.parseObject(entity.first(), StockVO::class.java)
    }

    fun findBySemaphore(semaphore: Boolean): List<StockVO> {
        logger.info("Finding one stock!")
        val entity = repository.findBySemaphore(semaphore)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        return DozerMapper.parseListObject(entity, StockVO::class.java)
    }

    fun create(stock: StockVO): StockVO {
        logger.info("Creating stock!")
        val entity = DozerMapper.parseObject(stock, Stock::class.java)
        val saved = repository.save(entity)
        return DozerMapper.parseObject(saved, StockVO::class.java)
    }

    fun update(stock: StockVO): StockVO {
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
        entity.semaphore = stock.semaphore
        return DozerMapper.parseObject(entity, StockVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("Deleting stock!")
        val entity: Stock = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }

    fun resetSemaphore() : Int {
        logger.info("Reseting Semaphore!")
        return repository.updateSemaphoreStatus(from = true, to = false)
    }

    fun activeSemaphore() : Int {
        logger.info("Activating Semaphore!")
        return repository.updateSemaphoreStatus(from = false, to = true)
    }
}