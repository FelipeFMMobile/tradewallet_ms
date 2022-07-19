package com.fmmobile.tradewallet.stock_extraction.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ApplicationException : RuntimeException {
    object DeTypes {
        const val STOCK_NOT_FOUND = "Symbol not found"
    }
    constructor(exception: String) : super(exception)
}