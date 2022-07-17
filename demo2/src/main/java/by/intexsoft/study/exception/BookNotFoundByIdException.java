package by.intexsoft.study.exception;

public class BookNotFoundByIdException extends RuntimeException{

    public BookNotFoundByIdException(Long id){
        super(String.format("Book not found by id: %d", id));
    }

}
