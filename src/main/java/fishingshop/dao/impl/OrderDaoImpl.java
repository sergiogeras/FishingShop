package fishingshop.dao.impl;

import fishingshop.dao.OrderDao;
import fishingshop.domain.customer.Customer;
import fishingshop.domain.order.Orders;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
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
        return sessionFactory.getCurrentSession().createQuery("select new Orders (o.orderId, sum(o.cost), o.orderDate," +
                " o.note, o.customer, o.payment, o.delivery, o.status) from  Orders as o group by o.orderId").list();
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

    @Override
    public List<Orders> getOrdersByCustomer(Customer customer) {
        return sessionFactory.getCurrentSession().createQuery("select new Orders (o.orderId, sum(o.cost), o.orderDate," +
                " o.payment, o.delivery, o.status, o.customer) from  Orders as o where o.customer=:c group by o.orderId")
                .setParameter("c", customer).list();

    }

    @Override
    public List<Orders> getOrderDetailsByCustomer(Customer customer, int orderId) {
        return sessionFactory.getCurrentSession().createQuery("from Orders where customer=:c and orderId=:id")
                .setParameter("c", customer)
                .setParameter("id", orderId)
                .list();
    }
}
