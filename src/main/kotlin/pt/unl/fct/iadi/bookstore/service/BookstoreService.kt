package pt.unl.fct.iadi.bookstore.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.bookstore.domain.Book
import pt.unl.fct.iadi.bookstore.domain.Review

@Service
class BookstoreService {

    private val books = mutableMapOf<String, Book>()
    private var reviewCounter = 1L

    fun listBooks(): List<Book> =
        books.values.toList()

    fun createBook(book: Book) {
        if (books.containsKey(book.isbn))
            throw BookAlreadyExistsException(book.isbn)

        books[book.isbn] = book
    }

    fun getBook(isbn: String): Book =
        books[isbn] ?: throw BookNotFoundException(isbn)

    fun replaceBook(isbn: String, book: Book) {
        books[isbn] = book
    }

    fun deleteBook(isbn: String) {
        if (books.remove(isbn) == null)
            throw BookNotFoundException(isbn)
    }

    fun patchBook(isbn: String, title: String?, author: String?, price: Double?, image: String?) {
        val book = getBook(isbn)

        title?.let { book.title = it }
        author?.let { book.author = it }
        price?.let { book.price = it }
        image?.let { book.image = it }
    }

    fun listReviews(isbn: String): List<Review> =
        getBook(isbn).reviews.values.toList()

    fun createReview(isbn: String, rating: Int, comment: String?): Review {

        val book = getBook(isbn)

        val review = Review(
            id = reviewCounter++,
            rating = rating,
            comment = comment
        )

        book.reviews[review.id] = review

        return review
    }

    fun getReview(isbn: String, id: Long): Review {
        val book = getBook(isbn)

        return book.reviews[id]
            ?: throw ReviewNotFoundException(id)
    }

    fun replaceReview(isbn: String, id: Long, rating: Int, comment: String?) {
        val review = getReview(isbn, id)

        review.rating = rating
        review.comment = comment
    }

    fun patchReview(isbn: String, id: Long, rating: Int?, comment: String?) {
        val review = getReview(isbn, id)

        rating?.let { review.rating = it }
        comment?.let { review.comment = it }
    }

    fun deleteReview(isbn: String, id: Long) {
        val book = getBook(isbn)

        if (book.reviews.remove(id) == null)
            throw ReviewNotFoundException(id)
    }
}