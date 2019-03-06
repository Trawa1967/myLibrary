package pl.trawex.libraryMySql.controllers;

import javassist.NotFoundException;
import javassist.compiler.NoFieldException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.trawex.libraryMySql.dtos.BookDto;
import pl.trawex.libraryMySql.entities.Book;
import pl.trawex.libraryMySql.mapper.BookMapper;
import pl.trawex.libraryMySql.services.BookService;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Controller

public class BookController {

    private BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    @RequestMapping("/")
    public String home(Model model) {
         String welcome = "Witam w moje prywatnej bibliotece";
         model.addAttribute("welcome", welcome);
         model.addAttribute("books", bookService.getAll());
         return "index";
       }


        //@RequestMapping("/newBook")
        @GetMapping("/newBook")//add
        public String addBook(
                @RequestParam(value = "title", required = false, defaultValue = "") String title,
                @RequestParam(value = "author", required = false, defaultValue = "") String author,
                @RequestParam(value = "kind", required = false, defaultValue = "") String kind,
                @RequestParam(value = "language", required = false, defaultValue = "") String language


        ) {

            Book book = new Book(title, author, kind, language);

            return "newBook";
        }

        //@RequestMapping("newBook")
        @PostMapping("addpost")
        public String addBook(@ModelAttribute Book book) {
            bookService.addBook(book);

            return "newBook";
        }

//

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
