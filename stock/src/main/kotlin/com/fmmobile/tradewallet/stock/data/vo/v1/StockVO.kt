package com.fmmobile.tradewallet.stock.data.vo.v1
import java.time.LocalDateTime

class StockVO {
    var id: Long = 0
    lateinit var symbol: String
    lateinit var name: String
    var maximum: Double = 0.0
    var minimum: Double = 0.0
    var average: Double = 0.0
    var open: Double = 0.0
    lateinit var volum: String
    lateinit var updated: LocalDateTime
    var price: Double = 0.0
    var variantion: Double = 0.0
    var last_price: Double = 0.0 // price last closed

    init { }
}
