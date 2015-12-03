package fishingshop.dao.impl;

import fishingshop.dao.RoleDao;
import fishingshop.domain.user.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sergei on 28.11.15.
 */

@Repository
@SuppressWarnings("unchecked")
public class RoleDaoImpl implements RoleDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Role> getRole(String role) {
        return sessionFactory.getCurrentSession().createQuery("from Role where role=:role").setParameter("role", role).list();
    }
}
