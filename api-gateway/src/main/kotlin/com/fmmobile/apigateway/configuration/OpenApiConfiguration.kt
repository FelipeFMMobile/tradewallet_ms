package com.fmmobile.apigateway.configuration

import org.springdoc.core.GroupedOpenApi
import org.springdoc.core.SwaggerUiConfigParameters
import org.springframework.cloud.gateway.route.RouteDefinitionLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import java.util.regex.Pattern.matches

@Configuration
class OpenApiConfiguration {
    @Bean
    @Lazy(false)
    public fun apis(config: SwaggerUiConfigParameters, locator: RouteDefinitionLocator) : List<GroupedOpenApi> {
        val definitions = locator.routeDefinitions.collectList().block()
        val regex = Regex(".*")
        definitions?.stream()?.filter { definition -> definition.id.matches(regex)  }
            ?.forEach { route -> route
                val name = route.id
                config.addGroup(name)
                GroupedOpenApi.builder()
                    .pathsToMatch("/" + name + "/**")
                    .group(name).build()
            }
        return ArrayList()
    }
}