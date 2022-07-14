package by.intexsoft.study.service;

import by.intexsoft.study.model.BookHistoryDto;

import java.util.List;

public interface BookHistoryService extends LibraryService<BookHistoryDto> {

    BookHistoryDto getLastBookHistoryByBookIdAndUserId(Long bookId, Long userId);
    List<BookHistoryDto> findBookHistoryByBookId(Long bookId);
}
