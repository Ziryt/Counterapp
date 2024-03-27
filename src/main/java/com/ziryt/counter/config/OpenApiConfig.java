package com.ziryt.counter.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

@OpenAPIDefinition(
        info = @Info(
                title = "Counter App Documentation",
                description = "This is a demo app that allows to create and work with counters",
                version = "1.1"
        ),
        servers = @Server(
                url = "http://localhost:8080"
        )
)
public class OpenApiConfig {
}
