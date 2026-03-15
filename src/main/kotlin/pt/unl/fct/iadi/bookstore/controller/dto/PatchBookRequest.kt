package pt.unl.fct.iadi.bookstore.controller.dto

data class PatchBookRequest(
    val title: String?,
    val author: String?,
    val price: Double?,
    val image: String?
)