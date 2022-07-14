package by.intexsoft.study.service.impl;

import by.intexsoft.study.mapper.AuthorMapper;
import by.intexsoft.study.model.AuthorDto;
import by.intexsoft.study.repository.AuthorDao;
import by.intexsoft.study.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao authorDao;
    private AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao, AuthorMapper authorMapper) {
        this.authorDao = authorDao;
        this.authorMapper = authorMapper;
    }


    @Override
    public List<AuthorDto> get10TheMostPopularAuthors() {
        return authorMapper.toDtos(authorDao.get10TheMostPopularAuthors());
    }

    @Override
    public AuthorDto findById(Long id) {
        return  authorMapper.toDto(authorDao.findById(id));
    }

    @Override
    public List<AuthorDto> findAll() {
        return authorMapper.toDtos(authorDao.findAll());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        authorDao.deleteById(id);
    }

    @Override
    @Transactional
    public AuthorDto create(AuthorDto authorDto) {
        return authorMapper.toDto(authorDao.createEntity(authorMapper.fromDto(authorDto)));
    }

    @Override
    @Transactional
    public AuthorDto update(AuthorDto authorDto) {
        return authorMapper.toDto(authorDao.updateEntity(authorMapper.fromDto(authorDto)));
    }

    @Override
    @Transactional
    public void patch(AuthorDto authorDto) {
        authorMapper.updateAuthorFromDto(authorDto, authorDao.findById(authorDto.getId()));
    }
}
