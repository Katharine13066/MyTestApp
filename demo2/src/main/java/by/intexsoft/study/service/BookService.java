package by.intexsoft.study.service;

import by.intexsoft.study.model.BookDto;

import java.util.List;

public interface BookService extends LibraryService<BookDto>{
    void takeBook(Long bookId, Long userId);
    void returnBook(Long bookId, Long userId);
    List<BookDto> get10TheMostPopularBooks();
}
