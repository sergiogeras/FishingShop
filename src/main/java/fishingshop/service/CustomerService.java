package fishingshop.service;

import fishingshop.domain.customer.Customer;

import java.util.List;

/**
 * Created by Сергей on 09.11.2015.
 */
public interface CustomerService {

    void addCustomer(Customer customer);
    void editCustomer(Customer customer);
    Customer getCustomerById(Integer id);
    List<Customer> getAllCustomers();
}
