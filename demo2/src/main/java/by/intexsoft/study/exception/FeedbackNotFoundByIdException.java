package by.intexsoft.study.exception;

public class FeedbackNotFoundByIdException extends RuntimeException{

    public FeedbackNotFoundByIdException(Long id){
        super(String.format("Feedback not found by id: %d", id));
    }

}
