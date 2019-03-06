package pl.trawex.libraryMySql.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    private String kind;
    private String language;

    public Book(String title, String author, String kind, String language) {
        this.title = title;
        this.author = author;
        this.kind = kind;
        this.language = language;
    }
}
