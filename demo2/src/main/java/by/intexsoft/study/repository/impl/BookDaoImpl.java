package by.intexsoft.study.repository.impl;

import by.intexsoft.study.model.BookHistory;
import by.intexsoft.study.model.Book;
import by.intexsoft.study.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BookDaoImpl extends DaoImpl<Book> implements BookDao {

    @Autowired
    public BookDaoImpl(EntityManager entityManager) {
        super(entityManager, Book.class);
    }

    @Override
    public List<Book> get10TheMostPopularBooks() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<BookHistory> root = criteriaQuery.from(BookHistory.class);
        Join<BookHistory, Book> joinOnBooks =  root.join("book");
        Expression<Long> countBookId = criteriaBuilder.count(root.get("bookId"));
        Expression<Long> countId = criteriaBuilder.count(root.get("id"));
        criteriaQuery.select(joinOnBooks)
                     .groupBy(joinOnBooks.get("bookName"), joinOnBooks.get("id"))
                     .having(criteriaBuilder.gt(countBookId, 0))
                     .orderBy(criteriaBuilder.desc(countId));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

}
