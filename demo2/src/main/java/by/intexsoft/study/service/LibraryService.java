package by.intexsoft.study.service;

import java.util.List;

public interface LibraryService<T> {
    T findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
    T create(T t);
    T update(T t);
    void patch(T t);

}