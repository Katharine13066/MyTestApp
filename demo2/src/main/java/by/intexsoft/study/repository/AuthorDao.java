package by.intexsoft.study.repository;

import by.intexsoft.study.model.Author;

import java.util.List;

public interface AuthorDao extends Dao<Author> {
    List<Author> get10TheMostPopularAuthors();
}
