package by.intexsoft.study.exception;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundByIdException extends EntityNotFoundException {

    public UserNotFoundByIdException(Long id){
        super(String.format("User not found by id: %d", id));
    }

}
