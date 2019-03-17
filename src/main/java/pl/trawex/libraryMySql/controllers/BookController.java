package pl.trawex.libraryMySql.controllers;

import javassist.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.trawex.libraryMySql.dtos.BookDto;
import pl.trawex.libraryMySql.entities.Author;
import pl.trawex.libraryMySql.entities.Book;
import pl.trawex.libraryMySql.entities.Kind;
import pl.trawex.libraryMySql.entities.Publisher;
import pl.trawex.libraryMySql.services.AuthorService;
import pl.trawex.libraryMySql.services.BookService;

import java.util.ArrayList;
import java.util.List;

@Controller

public class BookController {

    private BookService bookService;
//    private AuthorService authorService;
//    private List<Author> authors = new ArrayList<>();


    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    @RequestMapping("/")
    public String home(Model model) {
         //String welcome = "Witam w moje prywatnej bibliotece";
         //odel.addAttribute("welcome", welcome);
         model.addAttribute("books", bookService.getAll());
        System.out.println("Lista książek");
        System.out.println(model);
         return "index";
       }


        //@RequestMapping("/newBook")
        @GetMapping("/newBook")//add
        public String addBook(
                @RequestParam(value = "title", required = false, defaultValue = "") String title,
                @RequestParam(value = "author", required = false, defaultValue = "") Author author,
                @RequestParam(value = "kind", required = false, defaultValue = "") Kind kind,
                @RequestParam(value = "publisher", required = false, defaultValue = "") Publisher publisher,
                @RequestParam(value = "language", required = false, defaultValue = "") String language,
                Model model


        ) {
            model.addAttribute("authors", bookService.getAllAuthor());
            model.addAttribute("kinds", bookService.getAllKind());
            model.addAttribute("publishers", bookService.getAllPublisher());
//            authors=bookService.getAllAuthor();
//            authors=bookService.getAllAuthor();
//            System.out.println("---------authors 1-------------");
//            System.out.println(model);
//            System.out.println("----------------------");
            //model.addAttribute("authors", authors1);
//            List<Author> authors = bookService.getAllAuthor();
//            model.addAttribute("authors", authors);

            Book book = new Book(title, author, kind, publisher, language);
            //model.addAttribute("authors", )
            //model.addAttribute("authors", bookService.getAllAuthor());
            return "newBook";
        }

        //@RequestMapping("newBook")
        @PostMapping("addpost")
        public String addBook(@ModelAttribute Book book) {

            bookService.addBook(book);
            return "newBook";
        }

    @GetMapping("/newAuthor")//add
    public String addAuthor(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "surname", required = false, defaultValue = "") String surname

    ) {

        Author author = new Author(name, surname);
        return "newAuthor";
    }

    @PostMapping("addpostauthor")
    public String addAuthro(@ModelAttribute Author author) {

        bookService.addAuthor(author);
        return "newAuthor";
    }
    //---nowy rodzaj

    @GetMapping("/newKind")//add
    public String addKind(
            @RequestParam(value = "nameOfkind", required = false, defaultValue = "") String nameOfKind

    ) {

        Kind kind = new Kind(nameOfKind);
        return "newKind";
    }

    @PostMapping("addpostkind")
    public String addKind(@ModelAttribute Kind kind) {

        bookService.addKind(kind);
        return "newKind";
    }

    //nowy publisher
    @GetMapping("/newPublisher")//add
    public String addPublisher(
            @RequestParam(value = "namePub", required = false, defaultValue = "") String namePub,
            @RequestParam(value = "addressPub", required = false, defaultValue = "") String addressPub

    ) {

        Publisher publisher = new Publisher(namePub, addressPub);
        return "newPublisher";
    }

    @PostMapping("addpostpublisher")
    public String addPublisher(@ModelAttribute Publisher publisher) {

        bookService.addPublisher(publisher);
        return "newPublisher";
    }




        @GetMapping("del")
        public String delBook(@ModelAttribute(value = "id") int id) throws NotFoundException, ChangeSetPersister.NotFoundException {
            bookService.deleteBook(id);
            return "redirect:/";
        }

        @PostMapping("update")
        public String updateBook(@ModelAttribute BookDto book, Model model, Integer nr) {
            model.addAttribute("book", book);
            model.addAttribute("oldid", book.getId());

            //bookService

            return "update";
        }

        @PostMapping("/upd")
        public String updateBookById(@ModelAttribute BookDto book, @RequestParam Integer nr) throws NotFoundException {
            System.out.println("===========================");
            System.out.println(nr);
            bookService.updateBookById(nr, book);

            return "redirect:/";
        }


}
