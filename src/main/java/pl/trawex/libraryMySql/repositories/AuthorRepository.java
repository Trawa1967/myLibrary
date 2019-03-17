package pl.trawex.libraryMySql.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.trawex.libraryMySql.entities.Author;

import java.util.List;

public interface AuthorRepository  extends JpaRepository<Author, Integer> {



    @Override
    List<Author> findAll(Sort sort);

    @Override
    <S extends Author> S saveAndFlush(S s);
}
