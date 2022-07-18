package by.intexsoft.study.exception;

import javax.persistence.EntityNotFoundException;

public class AuthorNotFoundByIdException extends EntityNotFoundException {

    public AuthorNotFoundByIdException(Long id){
        super(String.format("Author not found by id: %d", id));
    }

}
