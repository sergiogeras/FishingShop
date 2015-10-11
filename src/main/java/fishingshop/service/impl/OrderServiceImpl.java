package fishingshop.service.impl;


import fishingshop.dao.OrderDao;
import fishingshop.domain.goods.Goods;
import fishingshop.domain.order.Orders;
import fishingshop.domain.order.OrderItem;
import fishingshop.service.GoodsService;
import fishingshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    GoodsService goodsService;

    @Override
    public void addOrder(List<OrderItem> orderItems){
        for(OrderItem item: orderItems){
            Orders orders =new Orders();
            orders.setOrderId(item.getOrderId());
            orders.setAmount(item.getAmount());
            orders.setOrderDate(new Date());
            orders.setCost(item.getGoods().getPrice()*item.getAmount());
            orders.setGoods(item.getGoods());

            //Changing count of goods

            Goods goods=goodsService.getGoodsById(item.getGoods().getId());
            goods.setGoodsAmount(goods.getGoodsAmount()-item.getAmount());
            goodsService.editGoods(goods);

            orderDao.addOrder(orders);

        }
    }

    @Override
    public void editOrders(List<OrderItem> orderItems) {

    }

    @Override
    public void deleteOrder(int orderId) {

        //Return goods to the store
        Goods goods;
        for(Orders orders: orderDao.getOrdersByOrderId(orderId)){
            goods=goodsService.getGoodsById(orders.getGoods().getId());
            goods.setGoodsAmount(goods.getGoodsAmount()+orders.getAmount());
            goodsService.editGoods(goods);
        }

        orderDao.deleteOrder(orderId);
    }

    @Override
    public void deleteOrderPositions(int[] id) {
        for(int positionId: id){

            //Return goods to the store
            Orders orders=orderDao.getOrderById(positionId);
            Goods goods=goodsService.getGoodsById(orders.getGoods().getId());
            goods.setGoodsAmount(goods.getGoodsAmount() + orders.getAmount());
            goodsService.editGoods(goods);

            orderDao.deleteOrderPosition(positionId);
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
