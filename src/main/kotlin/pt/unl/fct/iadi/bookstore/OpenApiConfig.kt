package pt.unl.fct.iadi.bookstore

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Bookstore REST API",
        version = "1.0",
        description = "REST API for managing books and reviews"
    ),
    tags = [
        Tag(name = "Books", description = "Operations related to books"),
        Tag(name = "Reviews", description = "Operations related to reviews")
    ]
)
class OpenApiConfig