package fishingshop.dao.impl;

import fishingshop.dao.UserDao;
import fishingshop.domain.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Сергей on 09.11.2015.
 */

@Repository
@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void editUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public User getUserById(Integer id) {
        return (User)sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }
}
