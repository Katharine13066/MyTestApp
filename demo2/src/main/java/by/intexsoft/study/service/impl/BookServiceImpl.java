package by.intexsoft.study.service.impl;

import by.intexsoft.study.mapper.BookMapper;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.model.BookDto;
import by.intexsoft.study.model.BookHistory;
import by.intexsoft.study.repository.BookDao;
import by.intexsoft.study.repository.BookHistoryDao;
import by.intexsoft.study.repository.UserDao;
import by.intexsoft.study.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookHistoryDao bookHistoryDao;

    @Autowired
    private UserDao userDao;


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
       // bookHistory.setBookId(bookId);
       // bookHistory.setUserId(userId);
        bookHistory.setReturnDate("");
        Date curDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
        bookHistory.setStartDate(simpleDateFormat.format(curDate));
     //   bookHistory.setBook(book);
        bookHistoryDao.createEntity(bookHistory);
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
    }

    @Override
    public List<BookDto> get10TheMostPopularBooks() {
        return bookMapper.toDtos(bookDao.get10TheMostPopularBooks());
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookDao.findById(id));
    }

    @Override
    public List<BookDto> findAll() {
        return bookMapper.toDtos(bookDao.findAll());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    @Transactional
    public BookDto create(BookDto bookDto) {
        return bookMapper.toDto(bookDao.createEntity(bookMapper.fromDto(bookDto)));
    }

    @Override
    @Transactional
    public BookDto update(BookDto bookDto) {
        return bookMapper.toDto(bookDao.updateEntity(bookMapper.fromDto(bookDto)));
    }

    @Override
    @Transactional
    public void patch(BookDto bookDto) {
        bookMapper.updateBookFromDto(bookDto, bookDao.findById(bookDto.getId()));
    }
}
