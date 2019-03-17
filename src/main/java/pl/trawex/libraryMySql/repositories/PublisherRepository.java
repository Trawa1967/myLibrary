package pl.trawex.libraryMySql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.trawex.libraryMySql.entities.Publisher;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    List<Publisher> findAll();

    @Override
    <S extends Publisher> S saveAndFlush(S s);
}
