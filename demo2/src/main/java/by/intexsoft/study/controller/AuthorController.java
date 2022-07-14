package by.intexsoft.study.controller;

import by.intexsoft.study.api.AuthorsApi;
import by.intexsoft.study.model.AuthorDto;
import by.intexsoft.study.model.BookDto;
import by.intexsoft.study.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController implements AuthorsApi {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public ResponseEntity<Void> createAuthor(AuthorDto authorDto) {
        authorService.create(authorDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> deleteAuthorById(Long id) {
        authorService.deleteById(id);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<AuthorDto>> findAllAuthors() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @Override
    public ResponseEntity<AuthorDto> findByIdAuthor(Long id) {
        AuthorDto authorDto = authorService.findById(id);

        if (authorDto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<> (authorDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AuthorDto>> get10TheMostPopularAuthors() {
        return ResponseEntity.ok(authorService.get10TheMostPopularAuthors());
    }

    @Override
    public ResponseEntity<Void> patchAuthor(AuthorDto authorDto) {
        authorService.patch(authorDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> updateAuthor(AuthorDto authorDto) {
        authorService.update(authorDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }
}
