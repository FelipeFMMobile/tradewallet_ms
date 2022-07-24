package com.fmmobile.tradewallet.stock.mapper.mocks

import com.fmmobile.tradewallet.stock.data.vo.v1.StockVO
import com.fmmobile.tradewallet.stock.model.Stock
import java.time.LocalDateTime
import kotlin.collections.ArrayList

class StockMock {
    fun mockEntity(): Stock {
        return mockEntity(0)
    }

    fun mockVO(): StockVO {
        return mockVO(0)
    }

    fun mockEntityList(): List<Stock> {
        val stocks: MutableList<Stock> = ArrayList<Stock>()
        for (i in 0..13) {
            stocks.add(mockEntity(i))
        }
        return stocks
    }

    fun mockVOList(): List<StockVO> {
        val stocks: MutableList<StockVO> = ArrayList<StockVO>()
        for (i in 0..13) {
            stocks.add(mockVO(i))
        }
        return stocks
    }

    fun mockEntity(number: Int): Stock {
        val stock = Stock(
            1,"MGLU3"
            ,"Magazine Luiza",3.4, 3.5
            ,3.9
            ,3.1
            ,"12 M"
            ,LocalDateTime.now()
            ,3.9, 3.2, 3.8)
        return stock
    }

    fun mockVO(number: Int): StockVO {
        val stock = StockVO(
            1,"MGLU3"
            ,"Magazine Luiza",3.4, 3.5
            ,3.9
            ,3.1
            ,"12 M"
            ,LocalDateTime.now()
            ,3.9, 3.2, 3.8)
        return stock
    }
}