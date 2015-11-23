package fishingshop.dao.impl;

import fishingshop.dao.CustomerDao;
import fishingshop.domain.customer.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Сергей on 09.11.2015.
 */

@Repository
@SuppressWarnings("unchecked")
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addCustomer(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
    }

    @Override
    public void editCustomer(Customer customer) {
        sessionFactory.getCurrentSession().update(customer);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return (Customer)sessionFactory.getCurrentSession().get(Customer.class, id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return sessionFactory.getCurrentSession().createQuery("from Customer").list();
    }

    @Override
    public Customer getCustomerByUser(String username) {
        return  (Customer)sessionFactory.getCurrentSession().createQuery("from Customer where user.username=:username").setParameter("username", username);
    }
}
