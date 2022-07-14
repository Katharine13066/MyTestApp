package by.intexsoft.study.repository.impl;

import by.intexsoft.study.model.BookHistory;
import by.intexsoft.study.repository.BookHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BookHistoryDaoImpl extends DaoImpl<BookHistory> implements BookHistoryDao {

    @Autowired
    public BookHistoryDaoImpl(EntityManager entityManager) {
        super(entityManager, BookHistory.class);
    }

    @Override
    public BookHistory getLastBookHistoryByBookIdAndUserId(Long bookId, Long userId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BookHistory> criteriaQuery = criteriaBuilder.createQuery(BookHistory.class);
        Root<BookHistory> root = criteriaQuery.from(BookHistory.class);
        criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("bookId"), bookId),
                criteriaBuilder.equal(root.get("returnDate"), ""),criteriaBuilder.equal(root.get("userId"), userId)));
        TypedQuery<BookHistory> typedQuery = getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList().get(0);
    }

    @Override
    public List<BookHistory> findBookHistoryByBookId(Long bookId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BookHistory> criteriaQuery = criteriaBuilder.createQuery(BookHistory.class);
        Root<BookHistory> root = criteriaQuery.from(BookHistory.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("bookId"), bookId));
        TypedQuery<BookHistory> typedQuery = getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

}

