package com.fmmobile.tradewallet.stock.controller

import com.fmmobile.tradewallet.stock.data.vo.v1.StockVO
import com.fmmobile.tradewallet.stock.services.StockService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Tag(name = "Stock endpoint")
@RestController
@RequestMapping("stock/v1")
class StockController {

    @Autowired
    lateinit var service: StockService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(): List<StockVO> {
        return service.findAll()
    }

    @GetMapping(value = ["/{symbol}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable(value = "symbol") symbol: String): StockVO {
        return service.findBySymbol(symbol)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody stock: StockVO): StockVO {
        return service.create(stock)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@RequestBody stock: StockVO): StockVO {
        return service.update(stock)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<*>? {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

    @GetMapping("status/{status}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun status(@PathVariable(value = "status") status: Boolean): List<StockVO> {
        return service.findBySemaphore(status)
    }

    @PostMapping("resetstatus")
    fun resetStatus(): ResponseEntity<*>? {
        service.resetSemaphore()
        return ResponseEntity.notFound().build<Any>()
    }

    @PostMapping("activestatus")
    fun activeStatus(): ResponseEntity<*>? {
        service.resetSemaphore()
        return ResponseEntity.notFound().build<Any>()
    }
}