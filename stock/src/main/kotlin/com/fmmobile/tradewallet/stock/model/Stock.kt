package com.fmmobile.tradewallet.stock.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "stock")
data class Stock (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(nullable = false, length = 10)
    var symbol: String,
    @Column(nullable = false)
    var name: String,
    @Column
    var maximum: Double,
    @Column
    var minimum: Double,
    @Column
    var average: Double,
    @Column
    var open: Double,
    @Column
    var volum: String,
    @Column
    var updated: LocalDateTime,
    @Column
    var price: Double,
    @Column
    var variantion: Double,
    @Column
    var last_price: Double, // price last closed
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    var semaphore: Boolean = false// price last closed
)
