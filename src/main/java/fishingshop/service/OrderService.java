package fishingshop.service;


import fishingshop.domain.customer.Customer;
import fishingshop.domain.order.OrderItem;
import fishingshop.domain.order.Orders;
import fishingshop.domain.order.Status;


import java.util.List;

public interface OrderService {
    void addOrder(List<OrderItem> orderItems);
    void editOrders(List<Orders> orderList, Status status);
    void deleteOrder(int orderId);
    void deleteOrderPositions(int [] id);
    List<Orders> getAllOrders();
    List<OrderItem> getOrdersByOrderId(int orderId);
    int getOrderId();
    int generateOrderId();
    List<Orders> getOrdersByCustomer(Customer customer);
    List<Orders> getOrderDetailsByCustomer(Customer customer, int orderId);
}
