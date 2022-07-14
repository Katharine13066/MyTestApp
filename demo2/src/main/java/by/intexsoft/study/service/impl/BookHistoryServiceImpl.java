package by.intexsoft.study.service.impl;

import by.intexsoft.study.mapper.AuthorMapper;
import by.intexsoft.study.mapper.BookHistoryMapper;
import by.intexsoft.study.mapper.BookMapper;
import by.intexsoft.study.model.BookHistory;
import by.intexsoft.study.model.BookHistoryDto;
import by.intexsoft.study.repository.BookHistoryDao;
import by.intexsoft.study.service.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookhistoryService")
public class BookHistoryServiceImpl implements BookHistoryService {

    private BookHistoryDao bookHistoryDao;

    private BookHistoryMapper bookHistoryMapper;

    private BookMapper bookMapper;

    private AuthorMapper authorMapper;

    @Autowired
    public BookHistoryServiceImpl(BookHistoryDao bookHistoryDao, BookHistoryMapper bookHistoryMapper, BookMapper bookMapper, AuthorMapper authorMapper) {
        this.bookHistoryDao = bookHistoryDao;
        this.bookHistoryMapper = bookHistoryMapper;
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
    }

    @Override
    public BookHistoryDto getLastBookHistoryByBookIdAndUserId(Long bookId, Long userId) {
        return bookHistoryMapper.toDto(bookHistoryDao.getLastBookHistoryByBookIdAndUserId(bookId, userId));
    }

    @Override
    public List<BookHistoryDto> findBookHistoryByBookId(Long bookId) {
        return bookHistoryMapper.toDtos(bookHistoryDao.findBookHistoryByBookId(bookId));
    }

    @Override
    public BookHistoryDto findById(Long id) {
        return bookHistoryMapper.toDto(bookHistoryDao.findById(id));
    }

    @Override
    public List<BookHistoryDto> findAll() {
        List<BookHistory> bookHistories = bookHistoryDao.findAll();
        return bookHistoryMapper.toDtos(bookHistories);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookHistoryDao.deleteById(id);
    }

    @Override
    @Transactional
    public BookHistoryDto create(BookHistoryDto bookHistoryDto) {
        return bookHistoryMapper.toDto(bookHistoryDao.createEntity(bookHistoryMapper.fromDto(bookHistoryDto)));
    }

    @Override
    @Transactional
    public BookHistoryDto update(BookHistoryDto bookHistoryDto) {
        return bookHistoryMapper.toDto(bookHistoryDao.updateEntity(bookHistoryMapper.fromDto(bookHistoryDto)));
    }

    @Override
    @Transactional
    public void patch(BookHistoryDto bookHistoryDto) {
        bookHistoryMapper.updateFeedbackFromDto(bookHistoryDto, bookHistoryDao.findById(bookHistoryDto.getId()));
    }
}
