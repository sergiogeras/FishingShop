package fishingshop.dao;

import fishingshop.domain.customer.Customer;

import java.util.List;

/**
 * Created by ������ on 09.11.2015.
 */
public interface CustomerDao {

    void addCustomer(Customer customer);
    void editCustomer(Customer customer);
    Customer getCustomerById(Integer id);
    List<Customer> getAllCustomers();
}
