package by.intexsoft.study.controller;

import by.intexsoft.study.api.BooksApi;
import by.intexsoft.study.model.BookDto;
import by.intexsoft.study.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements BooksApi {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public ResponseEntity<Void> createBook(BookDto bookDto) {
        bookService.create(bookDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> deleteBookById(Long id) {
        bookService.deleteById(id);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<BookDto>> findAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @Override
    public ResponseEntity<BookDto> findByIdBook(Long id) {
        BookDto bookDto = bookService.findById(id);

        if (bookDto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<> (bookDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BookDto>> get10TheMostPopularBooks() {
        return ResponseEntity.ok(bookService.get10TheMostPopularBooks());
    }

    @Override
    public ResponseEntity<Void> patchBook(BookDto bookDto) {
        bookService.patch(bookDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> returnBook(Long bookId, Long userId) {
        bookService.returnBook(bookId, userId);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> takeBook(Long bookId, Long userId) {
        bookService.takeBook(bookId, userId);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> updateBook(BookDto bookDto) {
        bookService.update(bookDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }
}
