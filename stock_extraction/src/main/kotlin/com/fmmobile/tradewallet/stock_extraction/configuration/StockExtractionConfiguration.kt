package com.fmmobile.tradewallet.stock_extraction.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("stock-tags")
class StockExtractionConfiguration {
    lateinit var name_tag: String
    lateinit var symbol_tag: String
    lateinit var price_tag: String
    lateinit var date_tag: String
    lateinit var open_tag: String
}