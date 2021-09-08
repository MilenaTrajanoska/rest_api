package com.rest.books_rest_api.model;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * Book model from database
 * */
@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long isbn;

    @NotEmpty
    @NonNull
    @Size(min = 5, max = 150)
    private String title;

    @NonNull
    @NotEmpty
    @Size(min = 5, max = 50)
    private String author;

    @NotEmpty
    @NonNull
    private String genre;

    @NonNull
    private Double price;
}
