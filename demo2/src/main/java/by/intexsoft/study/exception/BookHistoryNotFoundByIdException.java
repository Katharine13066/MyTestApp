package by.intexsoft.study.exception;

public class BookHistoryNotFoundByIdException extends RuntimeException{

    public BookHistoryNotFoundByIdException(Long id){
        super(String.format("BookHistory not found by id: %d", id));
    }

}
