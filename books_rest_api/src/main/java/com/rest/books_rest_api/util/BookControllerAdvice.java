package com.rest.books_rest_api.util;

import com.rest.books_rest_api.exception.BookAlreadyExistsException;
import com.rest.books_rest_api.exception.BookNotFoundException;
import com.rest.books_rest_api.exception.InvalidBookException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice for handling exceptions
 * */
@ControllerAdvice
class BookControllerAdvice {

    /**
     * Should return status code 404 Not Found if BookNotFoundException occurs
     * @param ex - BookNotFoundException
     * @return string - exception message
     * */
    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundException(BookNotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * Should return status code 403 Forbidden if BookAlreadyExistsException occurs
     * @param ex - BookAlreadyExistsException
     * @return string - exception message
     * */
    @ResponseBody
    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String bookAlreadyExistsException(BookAlreadyExistsException ex) {
        return ex.getMessage();
    }

    /**
     * Should return status code 400 Bad Request if InvalidBookException occurs
     * @param ex - InvalidBookException
     * @return string - exception message
     * */
    @ResponseBody
    @ExceptionHandler(InvalidBookException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidBookException(InvalidBookException ex) {
        return ex.getMessage();
    }
}