package com.rest.books_rest_api.api;

import com.rest.books_rest_api.dto.BookDTO;
import com.rest.books_rest_api.exception.BookAlreadyExistsException;
import com.rest.books_rest_api.exception.BookNotFoundException;
import com.rest.books_rest_api.exception.InvalidBookException;
import com.rest.books_rest_api.model.Book;
import com.rest.books_rest_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Rest api for books data
 * */
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    /**
     * Returns a single book from the database having the requested isbn as a BookDTO object
     * @param id - value of isbn for book to find
     * @throws BookNotFoundException if the book with the requested isbn does not exist
     * @return BookDTO - book with specified isbn
     * */
    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable Long id) {
        Book book = repository.findBookByIsbn(id).orElseThrow(() -> new BookNotFoundException(id));
        return mapBookToBookDTO(book);
    }

    /**
     * @return Collection of BookDTO - all books saved in the database
     * */
    @GetMapping("")
    public Collection<BookDTO> findBooks() {
        return repository.findAll()
                .stream().map(this::mapBookToBookDTO).collect(Collectors.toList());
    }

    /**
     * Creates a valid book in the database
     * @param book - valid book with values
     * @return BookDto - a single book
     * @throws InvalidBookException if passed isbn is null
     * @throws BookAlreadyExistsException if the passed book
     * for creation already exists in the database
     * */
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody @Valid BookDTO book) {
        Long isbn = book.getIsbn();

        if (isbn == null) throw new InvalidBookException();

        if (repository.findBookByIsbn(isbn).isPresent()) {
            throw new BookAlreadyExistsException(isbn);
        }

        Book saveBook = new Book();
        saveBook.setIsbn(isbn);
        saveBook.setTitle(book.getTitle());
        saveBook.setAuthor(book.getAuthor());
        saveBook.setPrice(book.getPrice());
        saveBook.setGenre(book.getGenre());
        Book saved = repository.save(saveBook);
        repository.flush();

        return mapBookToBookDTO(saved);
    }

    /**
     * Updates an existing book in the database
     * @param id - isbn of book to update
     * @param book - book with new values
     * @return BookDTO - book with updated values
     * @throws BookNotFoundException if the book does not exist in the database
     * */
    @PutMapping("/{id}")
    public BookDTO updateBook(
            @PathVariable("id") final Long id, @RequestBody @Valid BookDTO book) {

        Book updated = repository.findBookByIsbn(id).map(
                b -> {
                    b.setPrice(book.getPrice());
                    b.setGenre(book.getGenre());
                    b.setAuthor(book.getAuthor());
                    b.setTitle(book.getTitle());
                    return repository.save(b);
                }).orElseThrow(() -> new BookNotFoundException(id));
        repository.flush();
        return mapBookToBookDTO(updated);
    }

    /**
     * Deletes a book from the database with a given isbn
     * @param id - isbn of book to delete
     * @return long - isbn of deleted book
     * @throws BookNotFoundException if book does not exist in the database
     * */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Book deleted successfully")
    public long deleteBook(@PathVariable("id") final Long id) {
       return repository.findBookByIsbn(id).map(
               book -> {
                   repository.delete(book);
                   repository.flush();
                   return id;
               }).orElseThrow(() -> new BookNotFoundException(id));
    }

    /**
     * Mapper method from Book object to BookDTO object
     * @param book - book for mapping
     * @return BookDTO - object from BookDTO class
     * */
    private BookDTO mapBookToBookDTO(Book book) {
        return new BookDTO(book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPrice());
    }
}