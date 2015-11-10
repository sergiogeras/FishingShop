package fishingshop.service.impl;


import fishingshop.dao.OrderDao;
import fishingshop.domain.customer.Customer;
import fishingshop.domain.goods.Goods;
import fishingshop.domain.order.Delivery;
import fishingshop.domain.order.Orders;
import fishingshop.domain.order.OrderItem;
import fishingshop.domain.order.Payment;
import fishingshop.service.GoodsService;
import fishingshop.service.OrderService;
import fishingshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.context.FacesContext;
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
        FacesContext context=FacesContext.getCurrentInstance();

        int orderId=generateOrderId();
        for(OrderItem item: orderItems){
            if(item.getAmount()==0) break;
            Orders orders =new Orders();
            orders.setOrderId(orderId);
            orders.setAmount(item.getAmount());
            orders.setOrderDate(new Date());
            orders.setCost(item.getGoods().getPrice()*item.getAmount());
            orders.setGoods(item.getGoods());
            orders.setCustomer((Customer)context.getExternalContext().getSessionMap().get("customer"));
            orders.setDelivery((Delivery)context.getExternalContext().getSessionMap().get("delivery"));
            orders.setPayment((Payment)context.getExternalContext().getSessionMap().get("payment"));

            //Changing count of goods

            Goods goods=goodsService.getGoodsById(item.getGoods().getId());
            goods.setGoodsAmount(goods.getGoodsAmount()-item.getAmount());
            goodsService.editGoods(goods);

            orderDao.addOrder(orders);

        }
        orderItems.clear();
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

    /**
    Generate orderId by getting previous id from Orders
     */
    private int generateOrderId(){
        ArrayList<Orders> list= (ArrayList<Orders>) orderDao.getAllOrders();
        if(!list.isEmpty()){
            return  list.get((list.size()-1)).getOrderId()+1;
        } else {
            return 1;
        }

    }

}
