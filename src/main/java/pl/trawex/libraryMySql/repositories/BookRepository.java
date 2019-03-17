package pl.trawex.libraryMySql.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.trawex.libraryMySql.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    //@Query(value = "select b, a, k from Book b, Author a, Kind k where (b.id=a.id_author and b.id=k.id_kind)");
    List<Book> findAll();

    @Query(value = "select b from Book b where b.id=?1")
    Optional<Book> findBookById(int id);

//    @Query(value = "update (slect * from books a where .id=?1) update")

    @Override
    void deleteById(Integer integer);

    @Override
    Optional<Book> findById(Integer integer);



    //    @Override
//    <S extends Book> List<S> findAll(Example<S> example);
//-----
    @Override
    <S extends Book> List<S> saveAll(Iterable<S> iterable);

    @Override
    <S extends Book> S saveAndFlush(S s);

    @Override
    <S extends Book> S save(S s);
}
