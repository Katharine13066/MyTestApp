package by.intexsoft.study.service.impl;

import by.intexsoft.study.LibraryApplication;
import by.intexsoft.study.mapper.AuthorMapper;
import by.intexsoft.study.mapper.BookHistoryMapper;
import by.intexsoft.study.mapper.BookMapper;
import by.intexsoft.study.model.BookHistory;
import by.intexsoft.study.model.BookHistoryDto;
import by.intexsoft.study.repository.BookHistoryDao;
import by.intexsoft.study.service.BookHistoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookhistoryService")
public class BookHistoryServiceImpl implements BookHistoryService {

    private static final Logger logger = LogManager.getLogger(LibraryApplication.class);

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
        BookHistoryDto result = bookHistoryMapper.toDto(bookHistoryDao.getLastBookHistoryByBookIdAndUserId(bookId, userId));
        logger.info("Get last bookHistory by book and user ids ");
        return result;
    }

    @Override
    public List<BookHistoryDto> findBookHistoryByBookId(Long bookId) {
        List<BookHistoryDto> result = bookHistoryMapper.toDtos(bookHistoryDao.findBookHistoryByBookId(bookId));
        logger.info("Find bookHistory by book id");
        return result;
    }

    @Override
    public BookHistoryDto findById(Long id) {
        BookHistoryDto result = bookHistoryMapper.toDto(bookHistoryDao.findById(id));

        if(result == null){
            logger.warn("No bookHistory find by id");
            return null;
        }

        return result;
    }

    @Override
    public List<BookHistoryDto> findAll() {
        List<BookHistoryDto> result = bookHistoryMapper.toDtos( bookHistoryDao.findAll());
        logger.info("Get all bookHistory");
        return result;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookHistoryDao.deleteById(id);
        logger.info("Delete bookHistory by id");
    }

    @Override
    @Transactional
    public BookHistoryDto create(BookHistoryDto bookHistoryDto) {
        BookHistoryDto result = bookHistoryMapper.toDto(bookHistoryDao.createEntity(bookHistoryMapper.fromDto(bookHistoryDto)));
        logger.info("Create bookHistory");
        return result;
    }

    @Override
    @Transactional
    public BookHistoryDto update(BookHistoryDto bookHistoryDto) {
        BookHistoryDto result = bookHistoryMapper.toDto(bookHistoryDao.updateEntity(bookHistoryMapper.fromDto(bookHistoryDto)));
        logger.info("Update bookHistory");
        return result;
    }

    @Override
    @Transactional
    public void patch(BookHistoryDto bookHistoryDto) {
        bookHistoryMapper.updateFeedbackFromDto(bookHistoryDto, bookHistoryDao.findById(bookHistoryDto.getId()));
        logger.info("Patch bookHistory");
    }
}
