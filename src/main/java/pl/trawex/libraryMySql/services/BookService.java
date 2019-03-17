package pl.trawex.libraryMySql.services;

import javassist.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pl.trawex.libraryMySql.dtos.BookDto;
import pl.trawex.libraryMySql.entities.Author;
import pl.trawex.libraryMySql.entities.Book;
import pl.trawex.libraryMySql.entities.Kind;
import pl.trawex.libraryMySql.entities.Publisher;
import pl.trawex.libraryMySql.mappers.BookMapper;
import pl.trawex.libraryMySql.repositories.AuthorRepository;
import pl.trawex.libraryMySql.repositories.BookRepository;
import pl.trawex.libraryMySql.repositories.KindRepository;
import pl.trawex.libraryMySql.repositories.PublisherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private KindRepository kindRepository;
    private PublisherRepository publisherRepository;
    private BookMapper bookMapper;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       KindRepository kindRepository,
                       PublisherRepository publisherRepository,
                       BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository= authorRepository;
        this.kindRepository = kindRepository;
        this.publisherRepository = publisherRepository;
        this.bookMapper = bookMapper;
    }


    //wyświetlenie listy książek


    public List<Book>  getAll() {
        return bookRepository.findAll();

    }

    //---wyświetlanie wszystkich autorów
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    //---wyświetlanie wszystkich rodzajów
    public List<Kind> getAllKind() {
        return kindRepository.findAll();
    }

    //---wyświetlanie wszystkich publisheró
    public List<Publisher> getAllPublisher() {
        return publisherRepository.findAll();
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

    public Author addAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    public Kind addKind(Kind kind) {
        kindRepository.save(kind);
        return kind;
    }

    public Publisher addPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
        return publisher;
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

            //book.get().setId(numer);
            book.get().getTitle();
            book.get().getAuthor();
            book.get().getKind();
            book.get().getPublisher();
            book.get().getLanguage();

            bookRepository.save(book.get());

            return bookMapper.map(book.get());
        }
        throw new NotFoundException("Książka nie istnieje");
  }

}
