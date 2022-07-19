package com.fmmobile.tradewallet.stock_extraction.model

import java.time.LocalDateTime

data class Stock (
    val symbol: String,
    val name: String,
    val maximum: Double,
    var minimum: Double,
    val average: Double,
    val open: Double,
    var volum: String,
    var updated: LocalDateTime,
    var price: Double,
    var variantion: Double,
    var last_price: Double // price last closed
)
