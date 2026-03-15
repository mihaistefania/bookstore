package pt.unl.fct.iadi.bookstore.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import pt.unl.fct.iadi.bookstore.controller.dto.BookDTO
import pt.unl.fct.iadi.bookstore.controller.dto.CreateBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.PatchBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReviewDTO
import pt.unl.fct.iadi.bookstore.controller.dto.CreateReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.PatchReviewRequest

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema

@Tag(name = "Books")
@RequestMapping("/books")
interface BookstoreAPI{

    @Operation(summary = "List all books in the catalog")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Books successfully retrieved")
        ]
    )
    @GetMapping
    fun listBooks(): List<BookDTO>


    @Operation(summary = "Create a new book")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Book successfully created"),
            ApiResponse(responseCode = "400", description = "Validation error"),
            ApiResponse(responseCode = "409", description = "Book with the same ISBN already exists")
        ]
    )
    @PostMapping
    fun createBook(
        @Valid @RequestBody request: CreateBookRequest
    ): ResponseEntity<BookDTO>


    @Operation(summary = "Get a book by ISBN")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Book found",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = BookDTO::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Book not found"
            )
        ]
    )
    @GetMapping("/{isbn}")
    fun getBook(
        @Parameter(description = "ISBN of the book")
        @PathVariable isbn: String
    ): BookDTO


    @Operation(summary = "Replace a book (full update or create if it does not exist)")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Book replaced"),
            ApiResponse(responseCode = "201", description = "Book created"),
            ApiResponse(responseCode = "400", description = "Validation error")
        ]
    )
    @PutMapping("/{isbn}")
    fun replaceBook(
        @PathVariable isbn: String,
        @Valid @RequestBody request: ReplaceBookRequest
    ): ResponseEntity<Void>


    @Operation(summary = "Partially update a book")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Book updated"),
            ApiResponse(responseCode = "404", description = "Book not found"),
            ApiResponse(responseCode = "400", description = "Validation error")
        ]
    )
    @PatchMapping("/{isbn}")
    fun patchBook(
        @PathVariable isbn: String,
        @RequestBody request: PatchBookRequest
    ): ResponseEntity<Void>


    @Operation(summary = "Delete a book")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Book deleted"),
            ApiResponse(responseCode = "404", description = "Book not found")
        ]
    )
    @DeleteMapping("/{isbn}")
    fun deleteBook(
        @PathVariable isbn: String
    ): ResponseEntity<Void>


    @Operation(summary = "List all reviews of a book")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Reviews retrieved"),
            ApiResponse(responseCode = "404", description = "Book not found")
        ]
    )
    @GetMapping("/{isbn}/reviews")
    fun listReviews(
        @Parameter(description = "ISBN of the book")
        @PathVariable isbn: String
    ): List<ReviewDTO>


    @Operation(summary = "Create a review for a book")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Review created"),
            ApiResponse(responseCode = "404", description = "Book not found"),
            ApiResponse(responseCode = "400", description = "Validation error")
        ]
    )
    @PostMapping("/{isbn}/reviews")
    fun createReview(
        @Parameter(description = "ISBN of the book")
        @PathVariable isbn: String,
        @Valid @RequestBody request: CreateReviewRequest
    ): ResponseEntity<ReviewDTO>


    @Operation(summary = "Replace a review")
    @Tag(name = "Reviews")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Review replaced"),
            ApiResponse(responseCode = "404", description = "Book or review not found"),
            ApiResponse(responseCode = "400", description = "Validation error")
        ]
    )
    @PutMapping("/{isbn}/reviews/{id}")
    fun replaceReview(
        @PathVariable isbn: String,
        @PathVariable id: Long,
        @Valid @RequestBody request: ReplaceReviewRequest
    ): ResponseEntity<Void>


    @Operation(summary = "Partially update a review")
    @Tag(name = "Reviews")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Review updated"),
            ApiResponse(responseCode = "404", description = "Book or review not found"),
            ApiResponse(responseCode = "400", description = "Validation error")
        ]
    )
    @PatchMapping("/{isbn}/reviews/{id}")
    fun patchReview(
        @PathVariable isbn: String,
        @PathVariable id: Long,
        @RequestBody request: PatchReviewRequest
    ): ResponseEntity<Void>


    @Operation(summary = "Delete a review")
    @Tag(name = "Reviews")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Review deleted"),
            ApiResponse(responseCode = "404", description = "Book or review not found")
        ]
    )
    @DeleteMapping("/{isbn}/reviews/{id}")
    fun deleteReview(
        @PathVariable isbn: String,
        @PathVariable id: Long
    ): ResponseEntity<Void>
}