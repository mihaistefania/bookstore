package pt.unl.fct.iadi.bookstore.controller.dto

import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.URL

data class CreateBookRequest(

    @field:NotBlank
    val isbn: String,

    @field:Size(min = 1, max = 120)
    val title: String,

    @field:Size(min = 1, max = 80)
    val author: String,

    @field:DecimalMin("0.01")
    val price: Double,

    @field:URL
    val image: String
)