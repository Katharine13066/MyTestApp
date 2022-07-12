package by.intexsoft.study.repository.impl;

import by.intexsoft.study.model.User;
import by.intexsoft.study.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDaoImpl extends DaoImpl<User> implements UserDao {

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        super(entityManager, User.class);
    }

    @Override
    public User findByEmail(String email) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email));
        TypedQuery<User> typedQuery = getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public User findByUserName(String userName) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("userName"), userName));
        TypedQuery<User> typedQuery = getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }
}
