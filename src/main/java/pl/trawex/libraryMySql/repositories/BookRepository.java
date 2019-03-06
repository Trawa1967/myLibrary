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


//    //@Override
//
//
//    // List<Book> findBookById;
//
    List<Book> findAll();
//
//    Book findBookId(int id);
//
//    void deleteBook(int id);

//    UPDATE
//            (
//                    SELECT *
//                    FROM articles
//                    JOIN classification
//                    ON articles.articleID = classification.articleID
//                    WHERE classification.classID = 1
//            )
//    SET [updated_column] = updatevalue

    @Query(value = "select b from Book b where b.id=?1")
    Optional<Book> findBookById(int id);

//    @Query(value = "update (slect * from books a where .id=?1) update")

    @Override
    void deleteById(Integer integer);

    @Override
    Optional<Book> findById(Integer integer);

//    @Override
//    <S extends Book> List<S> findAll(Example<S> example);

    @Override
    <S extends Book> List<S> saveAll(Iterable<S> iterable);

    @Override
    <S extends Book> S saveAndFlush(S s);

    @Override
    <S extends Book> S save(S s);
}
