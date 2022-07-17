package by.intexsoft.study.exception;

public class AuthorNotFoundByIdException extends RuntimeException{

    public AuthorNotFoundByIdException(Long id){
        super(String.format("Author not found by id: %d", id));
    }
}
