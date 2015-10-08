package fishingshop.service.impl;


import fishingshop.dao.OrderDao;
import fishingshop.domain.order.Orders;
import fishingshop.domain.order.OrderItem;
import fishingshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public void addOrder(List<OrderItem> orderItems) {
        for(OrderItem item: orderItems){
            Orders orders =new Orders();
            orders.setOrderId(item.getOrderId());
            orders.setAmount(item.getAmount());
            orders.setCost(item.getGoods().getPrice()*item.getAmount());
            orders.setGoods(item.getGoods());
            orderDao.addOrder(orders);
        }
    }

    @Override
    public void editOrders(List<OrderItem> orderItems) {

    }

    @Override
    public void deleteOrder(int orderId) {
        orderDao.deleteOrder(orderId);
    }

    @Override
    public void deleteOrderPositions(int[] id) {
        for(int positionId: id){
            orderDao.deleteOrderPositions(positionId);
        }
    }

    @Override
    public List<OrderItem> getAllOrders() {

        List<OrderItem> orderItems=new ArrayList<>();
        OrderItem orderItem=new OrderItem();
        List<Orders> orders=orderDao.getAllOrders();

        for (Orders ord: orders){
            orderItem.setGoods(ord.getGoods());
            orderItem.setOrderId(ord.getOrderId());
            orderItem.setAmount(ord.getAmount());
            orderItems.add(orderItem);
        }

        return orderItems;
    }

    @Override
    public List<OrderItem> getOrdersByOrderId(int orderId) {
        List<OrderItem> orderItems=new ArrayList<>();
        OrderItem orderItem=new OrderItem();
        List<Orders> orders=orderDao.getOrdersByOrderId(orderId);

        for (Orders ord: orders){
            orderItem.setGoods(ord.getGoods());
            orderItem.setOrderId(ord.getOrderId());
            orderItem.setAmount(ord.getAmount());
            orderItems.add(orderItem);
        }

        return orderItems;
    }


}
