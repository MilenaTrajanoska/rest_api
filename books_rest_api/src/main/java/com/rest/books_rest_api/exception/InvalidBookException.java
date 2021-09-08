package com.rest.books_rest_api.exception;

/**
 * Invalid book exception
 * Thrown when passed book parameters are invalid
 * */
public class InvalidBookException extends RuntimeException{
    public InvalidBookException() {
        super("Book is invalid. Please check the format.");
    }
}
