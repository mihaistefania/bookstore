package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.URL

data class ReplaceBookRequest(

    @field:Schema(description = "ISBN of the book", example = "9780134685991")
    @field:NotBlank
    val isbn: String,

    @field:Schema(description = "Title of the book", example = "Effective Java")
    @field:Size(min = 1, max = 120)
    val title: String,

    @field:Schema(description = "Author of the book", example = "Joshua Bloch")
    @field:Size(min = 1, max = 80)
    val author: String,

    @field:Schema(description = "Price of the book", example = "45.0")
    @field:DecimalMin("0.01")
    val price: Double,

    @field:Schema(description = "URL of the book cover image", example = "https://example.com/book.jpg")
    @field:URL
    val image: String
)