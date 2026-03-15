package pt.unl.fct.iadi.bookstore.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.*
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import pt.unl.fct.iadi.bookstore.controller.dto.ErrorResponse
import pt.unl.fct.iadi.bookstore.service.*

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException::class)
    fun handleBookNotFound(
        ex: BookNotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {

        val lang = request.getHeader("Accept-Language") ?: "en"

        val message =
            if (lang.startsWith("pt"))
                "Livro com ISBN não encontrado"
            else
                ex.message!!

        val headers = HttpHeaders()
        headers["Content-Language"] =
            if (lang.startsWith("pt")) "pt" else "en"

        return ResponseEntity(
            ErrorResponse("NOT_FOUND", message),
            headers,
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(ReviewNotFoundException::class)
    fun handleReviewNotFound(ex: ReviewNotFoundException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse("NOT_FOUND", ex.message!!))

    @ExceptionHandler(BookAlreadyExistsException::class)
    fun handleConflict(ex: BookAlreadyExistsException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ErrorResponse("CONFLICT", ex.message!!))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse("VALIDATION_ERROR", "Invalid request"))
}