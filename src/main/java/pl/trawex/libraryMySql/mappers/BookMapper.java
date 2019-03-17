package pl.trawex.libraryMySql.mappers;

import org.springframework.stereotype.Component;
import pl.trawex.libraryMySql.dtos.BookDto;
import pl.trawex.libraryMySql.entities.Book;
import pl.trawex.libraryMySql.mapper.Mapper;

@Component
public class BookMapper  implements Mapper<Book, BookDto> {
    @Override
    public BookDto map(Book from) {
        return new BookDto(
                from.getId(),
                from.getTitle(),
                from.getAuthor().getIdAuthor(),
                from.getKind().getIdKind(),
                from.getPublisher().getIdPub(),
                from.getLanguage()
        );
    }

}
