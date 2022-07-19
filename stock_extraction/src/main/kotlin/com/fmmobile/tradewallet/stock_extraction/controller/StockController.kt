package com.fmmobile.tradewallet.stock_extraction.controller

import com.fmmobile.tradewallet.stock_extraction.configuration.StockExtractionConfiguration
import com.fmmobile.tradewallet.stock_extraction.exception.ApplicationException
import com.fmmobile.tradewallet.stock_extraction.exception.DecodeException
import com.fmmobile.tradewallet.stock_extraction.model.Stock
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Tag(name = "Stock endpoint")
@RestController
@RequestMapping("stock")
class StockController {

    @Autowired
    private lateinit var configuration: StockExtractionConfiguration

    @Autowired
    private lateinit var  environment: Environment

    private val GOOGLE_PAGE_URL_FINANCE = "https://www.google.com/finance/quote/"

    private val logger: Logger =
        LoggerFactory.getLogger(StockController::class.java)

    @Operation(summary = "Extrack stock from html web")
    @GetMapping("/{symbol}")
    @RateLimiter(name = "default")
    fun stock(@PathVariable symbol: String) : Stock {
        val port = environment.getProperty("local.server.port")
        val doc = loadHtml(symbol)
        val stock = decodeTags(doc)?.let { it ->
            checkStockObject(it)
            return it
        }
        return throw DecodeException(DecodeException.DeTypes.STOCK_ERROR)
    }

    @Operation(summary = "raw html web")
    @GetMapping("/{symbol}/html", MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    fun stockHtml(@PathVariable symbol: String) : String {
        val port = environment.getProperty("local.server.port")
        val doc = loadHtml(symbol)
        decodeTags(doc)
        return doc.html()
    }

    private fun loadHtml(symbol: String) : Document {
        val url = "$GOOGLE_PAGE_URL_FINANCE$symbol:BVMF"
        val headerMap = mutableMapOf<String, String>()
        headerMap["Accept"] = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
        headerMap["Accept-Language"] = "en-US,en,pt;q=0.5"
        headerMap["Cache-Control"] = "no-cache"
        headerMap["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8"
        headerMap["X-MicrosoftAjax"] = "Delta=true"
        val doc = Jsoup.connect(url)
            .userAgent("Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:28.0) Gecko/20100101 Firefox/28.0")
            .timeout(3000)
            .headers(headerMap)
            .get()
        return doc
    }

    private fun decodeTags(doc: Document) : Stock? {
        fun htmlForTag(tag: String) : String {
            return doc.getElementsByClass(tag).html()
        }
        if (doc.html().contains("We couldn't find any match for your search.")) {
            throw ApplicationException(ApplicationException.DeTypes.STOCK_NOT_FOUND)
            return null
        }
        val name = htmlForTag(configuration.name_tag).replace(" Preference Shares", "")
        val symbol = extractSymbol(htmlForTag(configuration.symbol_tag))
        val price = extractPrice(htmlForTag(configuration.price_tag))
        val date = extractDate(htmlForTag(configuration.date_tag))
        val values = doc.getElementsByClass(configuration.open_tag)
        if (values.count() == 0) {
            throw ApplicationException(ApplicationException.DeTypes.STOCK_NOT_FOUND)
            return null
        }
        val open = extractPrice(values[0].html())
        val variations = extractMinMaxVariation(values[1].html())
        val variationYear = extractMinMaxVariation(values[2].html())
        val capture = values[3].html()
        val volume = values[4].html()
        val plIndex = values[5].html()
        val dividends = values[6].html()
        val market = values[7].html()

        logger.info("Stock: {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}",
            name, symbol, price, date, open, variations,
            variationYear, capture, volume, plIndex, market)

        val max = variations?.second ?: 0.0
        val min = variations?.first ?: 0.0
        val average = max + min / 2
        val priceOpen = open ?: 0.0
        val priceNow = price ?: 0.0
        val variation = ((priceNow - priceOpen) / priceOpen) * 100
        return Stock(
            symbol ?: "",
            name ?: "",
            max,
            min,
            average,
            open ?: 0.0,
            volume,
            date ?: LocalDateTime.now(),
            price ?: 0.0,
            variation,
            open ?: 0.0)
    }

    private fun extractSymbol(symbol: String) : String? {
        return symbol.split("</span>").last().split(" ").first()
    }

    private fun extractPrice(price: String) : Double? {
        return price.removePrefix("R$").toDoubleOrNull()
    }

    private fun extractMinMaxVariation(variation: String) : Pair<Double?, Double?>? {
        val min = variation.split("-").first().trim()
        val max = variation.split("-").last().trim()
        return Pair(min.removePrefix("R$").toDoubleOrNull(),
            max.removePrefix("R$").toDoubleOrNull())
    }

    private fun extractVariation(variation: String) : Double? {
        return variation.removeSuffix("%").toDoubleOrNull()
    }

    private fun extractDate(date: String) : LocalDateTime? {
        val dateParts = date.substringBefore(" ·").split(" ")
        val months = arrayOf("Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez")
        val month = months.indexOfFirst { it -> it == dateParts.first() } + 1
        val day = dateParts[1].removeSuffix(",").toInt()
        val year = LocalDateTime.now().year
        val time = dateParts[2] + " " + dateParts[3]
        val parsedDate = "$day/$month/$year $time"
        return LocalDateTime.parse(parsedDate, DateTimeFormatter.ofPattern("dd/M/yyyy h:mm:ss a"))
    }

    private fun checkStockObject(stock: Stock) {
        if (stock.name.isEmpty()) {
            throw DecodeException(DecodeException.DeTypes.EMPTY_NAME)
        }
        if (stock.symbol.isEmpty()) {
            throw DecodeException(DecodeException.DeTypes.EMPTY_SYMBOL)
        }
        if (stock.price == 0.0) {
            throw DecodeException(DecodeException.DeTypes.PRICE_ERROR)
        }
    }
}