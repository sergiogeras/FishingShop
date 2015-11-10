package fishingshop.dao.impl;

import fishingshop.dao.PaymentDao;
import fishingshop.domain.order.Payment;
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
public class PaymentDaoImpl implements PaymentDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addPayment(Payment payment) {
        sessionFactory.getCurrentSession().save(payment);
    }

    @Override
    public void deletePayment(Integer id) {
        sessionFactory.getCurrentSession().delete(getPaymentById(id));
    }

    @Override
    public void editPayment(Payment payment) {
        sessionFactory.getCurrentSession().update(payment);
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return (Payment) sessionFactory.getCurrentSession().get(Payment.class, id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return sessionFactory.getCurrentSession().createQuery("from Payment ").list();
    }
}
