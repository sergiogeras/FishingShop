package fishingshop.dao.impl;

import fishingshop.dao.DeliveryDao;
import fishingshop.domain.order.Delivery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Сергей on 10.11.2015.
 */

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class DeliveryDaoImpl implements DeliveryDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addDelivery(Delivery delivery) {
        sessionFactory.getCurrentSession().save(delivery);
    }

    @Override
    public void deleteDelivery(Integer id) {
        sessionFactory.getCurrentSession().delete(getDeliveryById(id));
    }

    @Override
    public void editDelivery(Delivery delivery) {
        sessionFactory.getCurrentSession().update(delivery);
    }

    @Override
    public Delivery getDeliveryById(Integer id) {
        return (Delivery) sessionFactory.getCurrentSession().get(Delivery.class, id);
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return sessionFactory.getCurrentSession().createQuery("from Delivery ").list();
    }
}
