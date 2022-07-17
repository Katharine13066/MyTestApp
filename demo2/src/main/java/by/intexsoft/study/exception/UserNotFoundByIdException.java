package by.intexsoft.study.exception;

public class UserNotFoundByIdException extends RuntimeException {

    public UserNotFoundByIdException(Long id){
        super(String.format("User not found by id: %d", id));
    }

}
