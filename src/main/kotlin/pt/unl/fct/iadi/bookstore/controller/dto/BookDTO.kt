package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Representation of a book in the bookstore catalog")
data class BookDTO(

    @field:Schema(
        description = "Unique ISBN identifier of the book",
        example = "9780134685991"
    )
    val isbn: String,

    @field:Schema(
        description = "Title of the book",
        example = "Effective Java",
        minLength = 1,
        maxLength = 120
    )
    val title: String,

    @field:Schema(
        description = "Author of the book",
        example = "Joshua Bloch",
        minLength = 1,
        maxLength = 80
    )
    val author: String,

    @field:Schema(
        description = "Price of the book (must be greater than 0)",
        example = "45.0",
        minimum = "0.01"
    )
    val price: Double,

    @field:Schema(
        description = "URL of the book cover image",
        example = "https://example.com/book.jpg"
    )
    val image: String
)