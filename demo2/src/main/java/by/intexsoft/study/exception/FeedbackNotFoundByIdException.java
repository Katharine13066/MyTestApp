package by.intexsoft.study.exception;

import javax.persistence.EntityNotFoundException;

public class FeedbackNotFoundByIdException extends EntityNotFoundException {

    public FeedbackNotFoundByIdException(Long id){
        super(String.format("Feedback not found by id: %d", id));
    }

}
