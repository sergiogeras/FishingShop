package fishingshop.service.impl;

import fishingshop.dao.CustomerDao;
import fishingshop.domain.customer.Customer;
import fishingshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by Сергей on 09.11.2015.
 */

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    @Override
    public void editCustomer(Customer customer) {
        customerDao.editCustomer(customer);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getCustomerByUser(String username) {
        return customerDao.getCustomerByUser(username);
    }
}
