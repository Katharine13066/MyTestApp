package by.intexsoft.study.service.impl;

import by.intexsoft.study.LibraryApplication;
import by.intexsoft.study.exception.BookNotFoundByIdException;
import by.intexsoft.study.mapper.BookMapper;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.model.BookDto;
import by.intexsoft.study.model.BookHistory;
import by.intexsoft.study.repository.BookDao;
import by.intexsoft.study.repository.BookHistoryDao;
import by.intexsoft.study.repository.UserDao;
import by.intexsoft.study.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    private static final Logger logger = LogManager.getLogger(LibraryApplication.class);

    private BookDao bookDao;

    private BookMapper bookMapper;

    private BookHistoryDao bookHistoryDao;

    private UserDao userDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao, BookMapper bookMapper, BookHistoryDao bookHistoryDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.bookMapper = bookMapper;
        this.bookHistoryDao = bookHistoryDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void takeBook(Long bookId, Long userId) {
        Book book = bookDao.findById(bookId);
        book.setStatus(false);
        bookDao.updateEntity(book);
        BookHistory bookHistory = new BookHistory();
        bookHistory.setBook(book);
        bookHistory.setUser(userDao.findById(userId));
        bookHistory.setReturnDate("");
        Date curDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
        bookHistory.setStartDate(simpleDateFormat.format(curDate));
        bookHistoryDao.createEntity(bookHistory);
        logger.info("Take book");
    }

    @Override
    @Transactional
    public void returnBook(Long bookId, Long userId) {
        Book book = bookDao.findById(bookId);
        book.setStatus(true);
        bookDao.updateEntity(book);
        BookHistory bookHistory = bookHistoryDao.getLastBookHistoryByBookIdAndUserId(bookId, userId);
        Date curDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
        bookHistory.setReturnDate(simpleDateFormat.format(curDate));
        bookHistoryDao.updateEntity(bookHistory);
        logger.info("Return book");
    }

    @Override
    public List<BookDto> get10TheMostPopularBooks() {
        List<BookDto> result = bookMapper.toDtos(bookDao.get10TheMostPopularBooks());
        logger.info("Get top 10 books");
        return result;
    }

    @Override
    public BookDto findById(Long id) {
        BookDto result = bookMapper.toDto(bookDao.findById(id));
        if(result == null){
            logger.warn("No book find by id");
            throw new BookNotFoundByIdException(id);
        }
        logger.info("Find book by id");
        return result;
    }

    @Override
    public List<BookDto> findAll() {
        List<BookDto> result = bookMapper.toDtos(bookDao.findAll());
        logger.info("Get all books");
        return result;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookDao.deleteById(id);
        logger.info("Delete book by id");
    }

    @Override
    @Transactional
    public BookDto create(BookDto bookDto) {
        BookDto result = bookMapper.toDto(bookDao.createEntity(bookMapper.fromDto(bookDto)));
        logger.info("Create new book");
        return result;
    }

    @Override
    @Transactional
    public BookDto update(BookDto bookDto) {
        return bookMapper.toDto(bookDao.updateEntity(bookMapper.fromDto(bookDto)));
    }

    @Override
    @Transactional
    public void patch(BookDto bookDto) {
        Book book = bookDao.findById(bookDto.getId());
        if (book == null){
            logger.warn("No book find by id (patch method)");
            throw new BookNotFoundByIdException(bookDto.getId());
        }
        bookMapper.updateBookFromDto(bookDto,  book);
        logger.info("Patch book");
    }

}
