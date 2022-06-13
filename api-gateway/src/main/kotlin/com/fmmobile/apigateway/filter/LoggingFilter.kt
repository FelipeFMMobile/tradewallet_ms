package com.fmmobile.apigateway.filter
// TODO: Resolver o import do slf4j no kotlin
//import com.sun.org.slf4j.internal.LoggerFactory
//import org.springframework.cloud.gateway.filter.GatewayFilterChain
//import org.springframework.cloud.gateway.filter.GlobalFilter
//import org.springframework.stereotype.Component
//import org.springframework.web.server.ServerWebExchange
//import reactor.core.publisher.Mono
//
//@Component
//class LoggingFilter: GlobalFilter {
//    private var logger = LoggerFactory.getLogger(javaClass)
//
//    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
//        if (exchange != null) {
//            logger.debug("Output Path ${exchange.request.path}")
//        }
//        return chain!!.filter(exchange)
//    }
//}