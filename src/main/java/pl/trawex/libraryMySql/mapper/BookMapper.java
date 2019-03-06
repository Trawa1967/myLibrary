package pl.trawex.libraryMySql.mapper;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;
import pl.trawex.libraryMySql.dtos.BookDto;
import pl.trawex.libraryMySql.entities.Book;

import java.util.List;

@Component
public class BookMapper  implements Mapper<Book, BookDto>{

    public BookDto map(Book from) {

        return new BookDto(
                from.getId(),
                from.getTitle(),
                from.getAuthor(),
                from.getKind(),
                from.getLanguage()
        );
    }

}
