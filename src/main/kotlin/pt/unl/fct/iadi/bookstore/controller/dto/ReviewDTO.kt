package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Representation of a review belonging to a book")
data class ReviewDTO(

    @field:Schema(
        description = "Unique identifier of the review",
        example = "1"
    )
    val id: Long,

    @field:Schema(
        description = "Rating given by the reviewer (1 to 5)",
        example = "5",
        minimum = "1",
        maximum = "5"
    )
    val rating: Int,

    @field:Schema(
        description = "Optional comment about the book",
        example = "Excellent book for learning Java best practices",
        maxLength = 500
    )
    val comment: String?
)