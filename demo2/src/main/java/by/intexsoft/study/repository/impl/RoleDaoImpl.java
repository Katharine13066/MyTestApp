package by.intexsoft.study.repository.impl;


import by.intexsoft.study.model.Role;
import by.intexsoft.study.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RoleDaoImpl extends DaoImpl<Role> implements RoleDao {

    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        super(entityManager, Role.class);
    }

    @Override
    public Role findByRoleName(String roleName) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("roleName"), roleName));
        TypedQuery<Role> typedQuery = getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }
}
