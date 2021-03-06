package com.vines.domain;


import javax.persistence.*;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column(nullable = false,name = "isbn")
    private String isbn;

    @Column(nullable = false,name = "author")
    private String author;

    public Book (String name,String isbn,String author){
        this.name = name;
        this.isbn = isbn;
        this.author = author;
    }
    public Book(){

    }
    //此处省去get、set方法


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}