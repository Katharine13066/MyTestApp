package by.intexsoft.study.repository;

import by.intexsoft.study.model.Book;

import java.util.List;

public interface BookDao extends Dao<Book> {
    List<Book> get10TheMostPopularBooks();
}
