package fishingshop.dao.impl;

import fishingshop.dao.StatusDao;
import fishingshop.domain.order.Status;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Сергей on 17.11.2015.
 */

@Repository
@SuppressWarnings("unchecked")
public class StatusDaoImpl implements StatusDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addStatus(Status status) {
        sessionFactory.getCurrentSession().save(status);
    }

    @Override
    public void deleteStatus(Integer id) {
        sessionFactory.getCurrentSession().delete(id);
    }

    @Override
    public void editStatus(Status status) {
        sessionFactory.getCurrentSession().update(status);
    }

    @Override
    public Status getStatusById(Integer id) {
        return (Status)sessionFactory.getCurrentSession().get(Status.class, id);
    }

    @Override
    public List<Status> getAllStatuses() {
        return sessionFactory.getCurrentSession().createQuery("from Status").list();
    }
}
