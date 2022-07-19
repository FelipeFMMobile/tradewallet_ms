package com.fmmobile.tradewallet.stock_extraction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class StockExtractionApplication
fun main(args: Array<String>) {
	TimeZone.setDefault(TimeZone.getTimeZone("GMT-3:00"));
	runApplication<StockExtractionApplication>(*args)
}
