package pl.trawex.libraryMySql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.trawex.libraryMySql.entities.Kind;

import java.util.List;

public interface KindRepository extends JpaRepository<Kind, Integer> {

    List<Kind> findAll();

    @Override
    <S extends Kind> S saveAndFlush(S s);
}
