package pl.trawex.libraryMySql.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private int id;
    private String title;
    private Integer author;
    private Integer kind;
    private Integer publisher;
    private String language;

}
