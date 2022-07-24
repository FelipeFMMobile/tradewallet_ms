package com.fmmobile.tradewallet.stock.mapper

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper

class DozerMapper {
    companion object {
        private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()
        fun <O, D> parseObject(origin: O, destination: Class<D>) : D {
            return mapper.map(origin, destination)
        }
        fun <O, D> parseListObject(origin: List<O>, destination: Class<D>) : List<D> {
            val destinationObjects = ArrayList<D>()
            origin.forEach() { it ->
                destinationObjects.add(mapper.map(it, destination))
            }
            return destinationObjects
        }
    }
}