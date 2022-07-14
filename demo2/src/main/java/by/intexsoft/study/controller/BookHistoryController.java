package by.intexsoft.study.controller;

import by.intexsoft.study.api.BookhistoryApi;
import by.intexsoft.study.model.BookDto;
import by.intexsoft.study.model.BookHistoryDto;
import by.intexsoft.study.service.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookHistoryController implements BookhistoryApi {

    private BookHistoryService bookHistoryService;

    @Autowired
    public BookHistoryController(BookHistoryService bookHistoryService) {
        this.bookHistoryService = bookHistoryService;
    }

    @Override
    public ResponseEntity<Void> createBookHistory(BookHistoryDto bookHistoryDto) {
        bookHistoryService.create(bookHistoryDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> deleteBookHistoryById(Long id) {
        bookHistoryService.deleteById(id);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<BookHistoryDto>> findAllBookHistory() {
        return ResponseEntity.ok(bookHistoryService.findAll());
    }

    @Override
    public ResponseEntity<BookHistoryDto> findByIdBookHistory(Long id) {
        BookHistoryDto bookHistoryDto = bookHistoryService.findById(id);

        if (bookHistoryDto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<> (bookHistoryDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BookHistoryDto>> getBookHistoryByBookId(Long id) {
        List<BookHistoryDto> bookHistoryDto = bookHistoryService.findBookHistoryByBookId(id);

        if (bookHistoryDto.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<> (bookHistoryDto, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> patchBookHistory(BookHistoryDto bookHistoryDto) {
        bookHistoryService.patch(bookHistoryDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> updateBookHistory(BookHistoryDto bookHistoryDto) {
        bookHistoryService.update(bookHistoryDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }
}
