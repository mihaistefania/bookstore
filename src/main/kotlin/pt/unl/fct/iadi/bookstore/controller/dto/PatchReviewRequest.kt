package pt.unl.fct.iadi.bookstore.controller.dto
import io.swagger.v3.oas.annotations.media.Schema

data class PatchReviewRequest(
    @field:Schema(
        description = "Rating between 1 and 5",
        minimum = "1",
        maximum = "5",
        example = "4"
    )
    val rating: Int?,

    @field:Schema(
        description = "Optional comment",
        maxLength = 500,
        example = "Updated comment"
    )
    val comment: String?
)