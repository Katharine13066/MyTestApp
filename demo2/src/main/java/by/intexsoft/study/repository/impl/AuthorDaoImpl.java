package by.intexsoft.study.repository.impl;

import by.intexsoft.study.model.Book;
import by.intexsoft.study.model.BookHistory;
import by.intexsoft.study.model.Author;
import by.intexsoft.study.repository.AuthorDao;
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
public class AuthorDaoImpl extends DaoImpl<Author> implements AuthorDao {

    @Autowired
    public AuthorDaoImpl(EntityManager entityManager) {
        super(entityManager, Author.class);
    }

    @Override
    public List<Author> get10TheMostPopularAuthors() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<BookHistory> root = criteriaQuery.from(BookHistory.class);
        Join<BookHistory, Book> joinOnBooks =  root.join("book");
        Join<Book, Author> joinOnAuthors =  joinOnBooks.join("authors");
        Expression<Long> countBookId = criteriaBuilder.count(root.get("bookId"));
        Expression<Long> countId = criteriaBuilder.count(root.get("id"));
        criteriaQuery.select(joinOnAuthors)
                     .groupBy(joinOnAuthors.get("authorName"), joinOnAuthors.get("id"))
                     .having(criteriaBuilder.gt(countBookId, 0))
                     .orderBy(criteriaBuilder.desc(countId));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

}
