package fishingshop.dao;

import fishingshop.domain.customer.Customer;
import fishingshop.domain.order.Orders;

import java.util.List;


public interface OrderDao {
    void addOrder(Orders orders);
    void editOrder(Orders orders);
    void deleteOrder(int orderId);
    void deleteOrderPosition(int id);   //delete certain position from Order
    List<Orders> getAllOrders();
    List<Orders> getOrdersByOrderId(int orderId);
    Orders getOrderById(int id);
    List<Orders> getOrdersByCustomer(Customer customer);
}
