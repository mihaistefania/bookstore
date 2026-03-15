package pt.unl.fct.iadi.bookstore.service

class BookNotFoundException(isbn: String)
    : RuntimeException("Book with ISBN $isbn not found")