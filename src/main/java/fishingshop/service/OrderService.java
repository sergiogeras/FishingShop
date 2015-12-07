package fishingshop.service;


import fishingshop.domain.customer.Customer;
import fishingshop.domain.order.OrderItem;
import fishingshop.domain.order.Orders;


import java.util.List;

public interface OrderService {
    void addOrder(List<OrderItem> orderItems);
    void editOrders(List<OrderItem> orderItems);
    void deleteOrder(int orderId);
    void deleteOrderPositions(int [] id);
    List<OrderItem> getAllOrders();
    List<OrderItem> getOrdersByOrderId(int orderId);
    int getOrderId();
    int generateOrderId();
    List<Orders> getOrdersByCustomer(Customer customer);
}
