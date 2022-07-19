package com.fmmobile.tradewallet.stock_extraction.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class DecodeException : RuntimeException {
    object DeTypes {
        const val EMPTY_NAME = "empty name"
        const val EMPTY_SYMBOL = "symbol name"
        const val PRICE_ERROR = "price error"
        const val STOCK_ERROR = "stock generate error"
    }
    constructor(exception: String) : super(exception)
}