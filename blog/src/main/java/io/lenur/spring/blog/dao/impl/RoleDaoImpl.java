package io.lenur.spring.blog.dao.impl;

import io.lenur.spring.blog.dao.RoleDao;
import io.lenur.spring.blog.domain.Role;
import io.lenur.spring.blog.domain.RoleName;
import io.lenur.spring.blog.exception.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Optional;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    @Override
    public Optional<Role> findByName(RoleName name) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "SELECT r " +
                        "FROM Role r " +
                        "WHERE r.name=:name"
                    , Role.class);
            query.setParameter("name", name);
            Role role = (Role) query.getSingleResult();
            return Optional.ofNullable(role);
        } catch (HibernateException e) {
            String msg = "There is something wrong in getting role";
            throw new DaoException(msg, e);
        }
    }
}
