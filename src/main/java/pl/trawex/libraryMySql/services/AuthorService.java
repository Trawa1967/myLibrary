package pl.trawex.libraryMySql.services;

import pl.trawex.libraryMySql.entities.Author;
import pl.trawex.libraryMySql.repositories.AuthorRepository;

import java.util.List;

public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author>  getAll() {
        return authorRepository.findAll();
    }
}
