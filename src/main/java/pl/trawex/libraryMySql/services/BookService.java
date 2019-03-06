package pl.trawex.libraryMySql.services;

import javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.trawex.libraryMySql.dtos.BookDto;
import pl.trawex.libraryMySql.entities.Book;
import pl.trawex.libraryMySql.mapper.BookMapper;
import pl.trawex.libraryMySql.repositories.BookRepository;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    //wyświetlenie listy książek


    public List<Book>  getAll() {
        return bookRepository.findAll();

    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).get();
    }

    //-------------------------DTO-----------------

    public Optional<Book> getBookByIdOptional(int id) {
        return bookRepository.findById(id);
    }

    public List<BookDto> getBookDto() {

        List<BookDto> bookDTOS = new ArrayList<>();

        for (Book book : bookRepository.findAll()
        ) {
            bookDTOS.add(bookMapper.map(book));

        }
        return bookDTOS;
    }

    //dodanie nowej książki

    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    //wyświetlenie szczegółów

//    public void getBook(int id) {
//        Book book = bookRepository.findBookId(id);
//    }

    //usunięcie książki

  public boolean deleteBook(int id) throws ChangeSetPersister.NotFoundException {
      Optional<Book> book=bookRepository.findBookById(id);
      if(book.isPresent()) {
          bookRepository.deleteById(book.get().getId());
          return true;
      }else {
          return false;
      }
  }

  public BookDto updateBookById(Integer numer, BookDto bookDto) throws NotFoundException {
        Optional<Book> book = bookRepository.findBookById(numer);

        if(book.isPresent()) {

            book.get().setTitle(bookDto.getTitle());
            book.get().setAuthor(bookDto.getAuthor());
            book.get().setKind(bookDto.getKind());
            book.get().setId(bookDto.getId());

            bookRepository.save(book.get());

            return bookMapper.map(book.get());
        }
        throw new NotFoundException("Książka nie istnieje");
  }

}
