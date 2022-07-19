package by.intexsoft.study.service.impl;

import by.intexsoft.study.LibraryApplication;
import by.intexsoft.study.exception.AuthorNotFoundByIdException;
import by.intexsoft.study.mapper.AuthorMapper;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.model.AuthorDto;
import by.intexsoft.study.repository.AuthorDao;
import by.intexsoft.study.service.AuthorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LogManager.getLogger(LibraryApplication.class);

    private AuthorDao authorDao;
    private AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao, AuthorMapper authorMapper) {
        this.authorDao = authorDao;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDto> get10TheMostPopularAuthors() {
        List<AuthorDto> result = authorMapper.toDtos(authorDao.get10TheMostPopularAuthors());
        logger.info("Get top 10 authors");
        return result;
    }

    @Override
    public AuthorDto findById(Long id) {
        AuthorDto result = authorMapper.toDto(authorDao.findById(id));
        if(result == null){
            logger.warn("No authors find by id");
            throw new AuthorNotFoundByIdException(id);
        }
        logger.info("Find author by id");
        return  result;
    }

    @Override
    public List<AuthorDto> findAll() {
        List<AuthorDto> result = authorMapper.toDtos(authorDao.findAll());
        logger.info("Get all authors");
        return result;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        authorDao.deleteById(id);
        logger.info("Delete author by id");
    }

    @Override
    @Transactional
    public AuthorDto create(AuthorDto authorDto) {
        AuthorDto result = authorMapper.toDto(authorDao.createEntity(authorMapper.fromDto(authorDto)));
        logger.info("Create author");
        return result;
    }

    @Override
    @Transactional
    public AuthorDto update(AuthorDto authorDto) {
       return authorMapper.toDto(authorDao.updateEntity(authorMapper.fromDto(authorDto)));
    }

    @Override
    @Transactional
    public void patch(AuthorDto authorDto) {
        Author author = authorDao.findById(authorDto.getId());
        if (author == null){
            logger.warn("No authors find by id (patch method)");
            throw new AuthorNotFoundByIdException(authorDto.getId());
        }
        authorMapper.updateAuthorFromDto(authorDto, authorDao.findById(authorDto.getId()));
        logger.info("Patch author");
    }

}
