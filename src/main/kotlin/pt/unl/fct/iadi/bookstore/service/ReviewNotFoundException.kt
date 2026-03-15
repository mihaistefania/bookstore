package pt.unl.fct.iadi.bookstore.service

class ReviewNotFoundException(id: Long)
    : RuntimeException("Review $id not found")