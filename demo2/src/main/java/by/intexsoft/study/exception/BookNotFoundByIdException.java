package by.intexsoft.study.exception;

import javax.persistence.EntityNotFoundException;

public class BookNotFoundByIdException extends EntityNotFoundException {

    public BookNotFoundByIdException(Long id){
        super(String.format("Book not found by id: %d", id));
    }

}
