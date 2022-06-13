package com.fmmobile.namingserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class NamingServerApplication

fun main(args: Array<String>) {
	runApplication<NamingServerApplication>(*args)
}
