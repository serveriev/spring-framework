package io.lenur.spring.boot.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    private BigDecimal price;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(mappedBy = "books")
    private List<Category> categories = new ArrayList<>();
}
