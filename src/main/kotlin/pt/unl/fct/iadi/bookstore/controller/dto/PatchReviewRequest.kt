package pt.unl.fct.iadi.bookstore.controller.dto

data class PatchReviewRequest(
    val rating: Int?,
    val comment: String?
)