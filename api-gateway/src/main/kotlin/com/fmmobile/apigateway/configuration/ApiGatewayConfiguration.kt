package com.fmmobile.apigateway.configuration

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//// Moved to application.yml file

//@Configuration
//class ApiGatewayConfiguration {
//
//    @Bean
//    public fun gatewayrouter(builder: RouteLocatorBuilder) : RouteLocator {
//        return builder.routes()
//            .route { p ->
//                p.path("/get")
//                    .filters { f ->
//                        f.addRequestHeader("Hello", "World")
//                        f.addRequestParameter("Hello", "World2")
//                    }
//                .uri("http://httpbin.org:80")
//            }
////            .route { p ->
////                p.path("/cambio/**")
////                    .uri("lb://cambio")
////            }
////            .route { p ->
////                p.path("/book-service/**")
////                    .uri("lb://book")
////            }
//            .build()
//    }
//}