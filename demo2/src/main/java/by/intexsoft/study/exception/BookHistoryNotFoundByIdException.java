package by.intexsoft.study.exception;

import javax.persistence.EntityNotFoundException;

public class BookHistoryNotFoundByIdException extends EntityNotFoundException {

    public BookHistoryNotFoundByIdException(Long id){
        super(String.format("BookHistory not found by id: %d", id));
    }

}
