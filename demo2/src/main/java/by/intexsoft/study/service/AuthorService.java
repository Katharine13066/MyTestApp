package by.intexsoft.study.service;

import by.intexsoft.study.model.AuthorDto;

import java.util.List;

public interface AuthorService extends LibraryService<AuthorDto>{
    List<AuthorDto> get10TheMostPopularAuthors();

}
