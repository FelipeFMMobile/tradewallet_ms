package com.fmmobile.tradewallet.stock_extraction.exception.model

import java.io.Serializable
import java.util.*

data class ExceptionResponse(val timestamp: Date,
                             val message: String,
                             val details: String) : Serializable {}