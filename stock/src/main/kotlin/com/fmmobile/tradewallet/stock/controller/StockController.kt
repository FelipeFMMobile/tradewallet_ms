package com.fmmobile.tradewallet.stock.controller

import com.fmmobile.tradewallet.stock.model.Stock
import com.fmmobile.tradewallet.stock.services.StockService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Tag(name = "Stock endpoint")
@RestController
@RequestMapping("stock")
class StockController {

    @Autowired
    lateinit var service: StockService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(): List<Stock> {
        return service.findAll()
    }

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable(value = "id") id: Long): Stock {
        return service.findById(id)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody stock: Stock): Stock {
        return service.create(stock)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@RequestBody stock: Stock): Stock {
        return service.update(stock)
    }


    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<*>? {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}