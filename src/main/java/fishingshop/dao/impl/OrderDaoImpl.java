package fishingshop.dao.impl;

import fishingshop.dao.OrderDao;
import fishingshop.domain.order.Orders;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@SuppressWarnings("unchecked")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addOrder(Orders orders) {
        sessionFactory.getCurrentSession().merge(orders);
    }

    @Override
    public void editOrder(Orders orders) {
        sessionFactory.getCurrentSession().update(orders);
    }

    @Override
    public void deleteOrder(int orderId) {
        sessionFactory.getCurrentSession().createQuery("delete Orders where orderId=:orderId")
                .setParameter("orderId", orderId).executeUpdate();
    }

    @Override
    public void deleteOrderPosition(int id) {
        sessionFactory.getCurrentSession().delete(getOrderById(id));
    }

    @Override
    public List<Orders> getAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("from Orders").list();
    }

    @Override
    public List<Orders> getOrdersByOrderId(int orderId) {
        return sessionFactory.getCurrentSession().createQuery("from Orders where orderId=:orderId")
                .setParameter("orderId", orderId).list();
    }

    @Override
    public Orders getOrderById(int id) {
        return (Orders)sessionFactory.getCurrentSession().get(Orders.class, id);
    }
}
