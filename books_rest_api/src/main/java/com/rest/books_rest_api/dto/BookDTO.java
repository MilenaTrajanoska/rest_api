package com.rest.books_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Book DTO class
 * Used for transferring data from-to the rest api
 * */
@Getter
@Setter
@AllArgsConstructor
public class BookDTO {
    @NonNull
    private Long isbn;

    @NotEmpty
    @NonNull
    @Size(min = 5, max = 150)
    private String title;

    @NonNull
    @Size(min = 5, max = 50)
    private String author;

    @NotEmpty
    @NonNull
    private String genre;

    @NonNull
    private Double price;
}
