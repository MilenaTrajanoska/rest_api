package com.rest.books_rest_api.exception;
/**
 * Book already exists exception
 * Thrown when trying to create a book that already exists
 **/
public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(Long bookIsbn) {
        super("Book with isbn: " + bookIsbn + " already exists");
    }
}
