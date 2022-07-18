package by.intexsoft.study.repository;

import by.intexsoft.study.model.BookHistory;

import java.util.List;

public interface BookHistoryDao extends Dao<BookHistory> {
    BookHistory getLastBookHistoryByBookIdAndUserId(Long bookId, Long userId);
    List<BookHistory> findBookHistoryByBookId(Long bookId);

}
