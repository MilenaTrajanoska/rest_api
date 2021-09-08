package com.rest.books_rest_api.exception;

/**
 * Book not found exception
 * Thrown when book with requested isbn is not found
 * */
public class BookNotFoundException extends RuntimeException{
    private Long bookIsbn;
    public BookNotFoundException(Long bookIsbn) {
        super("Book with isbn: " + bookIsbn + " could not be found");
        this.bookIsbn = bookIsbn;
    }
}
