package pt.unl.fct.iadi.bookstore.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pt.unl.fct.iadi.bookstore.controller.dto.BookDTO
import pt.unl.fct.iadi.bookstore.controller.dto.CreateBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.PatchBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReviewDTO
import pt.unl.fct.iadi.bookstore.controller.dto.CreateReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.PatchReviewRequest
import pt.unl.fct.iadi.bookstore.domain.Book
import pt.unl.fct.iadi.bookstore.service.BookstoreService
import java.net.URI

@RestController
class BookstoreController(
    private val service: BookstoreService
) : BookstoreAPI {

    override fun listBooks(): List<BookDTO> =
        service.listBooks().map {
            BookDTO(it.isbn, it.title, it.author, it.price, it.image)
        }

    override fun createBook(request: CreateBookRequest): ResponseEntity<BookDTO> {

        val book = Book(
            request.isbn,
            request.title,
            request.author,
            request.price,
            request.image
        )

        service.createBook(book)

        val location: URI = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{isbn}")
            .buildAndExpand(book.isbn)
            .toUri()

        return ResponseEntity.created(location)
            .body(BookDTO(book.isbn, book.title, book.author, book.price, book.image))
    }

    override fun getBook(isbn: String): BookDTO {
        val book = service.getBook(isbn)

        return BookDTO(
            book.isbn,
            book.title,
            book.author,
            book.price,
            book.image
        )
    }

    override fun replaceBook(isbn: String, request: ReplaceBookRequest): ResponseEntity<Void> {

        val book = Book(
            isbn,
            request.title,
            request.author,
            request.price,
            request.image
        )

        service.replaceBook(isbn, book)

        return ResponseEntity.ok().build()
    }

    override fun patchBook(isbn: String, request: PatchBookRequest): ResponseEntity<Void> {

        service.patchBook(
            isbn,
            request.title,
            request.author,
            request.price,
            request.image
        )

        return ResponseEntity.ok().build()
    }

    override fun deleteBook(isbn: String): ResponseEntity<Void> {

        service.deleteBook(isbn)

        return ResponseEntity.noContent().build()
    }

    override fun listReviews(isbn: String): List<ReviewDTO> =
        service.listReviews(isbn).map {
            ReviewDTO(it.id, it.rating, it.comment)
        }

    override fun createReview(isbn: String, request: CreateReviewRequest): ResponseEntity<ReviewDTO> {

        val review = service.createReview(
            isbn,
            request.rating,
            request.comment
        )

        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(review.id)
            .toUri()

        return ResponseEntity.created(location)
            .body(ReviewDTO(review.id, review.rating, review.comment))
    }

    override fun replaceReview(isbn: String, id: Long, request: ReplaceReviewRequest): ResponseEntity<Void> {

        service.replaceReview(
            isbn,
            id,
            request.rating,
            request.comment
        )

        return ResponseEntity.ok().build()
    }

    override fun patchReview(isbn: String, id: Long, request: PatchReviewRequest): ResponseEntity<Void> {

        service.patchReview(
            isbn,
            id,
            request.rating,
            request.comment
        )

        return ResponseEntity.ok().build()
    }

    override fun deleteReview(isbn: String, id: Long): ResponseEntity<Void> {

        service.deleteReview(isbn, id)

        return ResponseEntity.noContent().build()
    }
}