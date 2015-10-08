package fishingshop.service.shop;

import fishingshop.domain.goods.Goods;
import fishingshop.domain.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Cart of goods
 * List of goods stored in List<OrderItem>, in sessionMap
 */

@Component
@Scope("session")
public class Cart {

    private List<OrderItem> orderItems;
    private int amount;
    private Goods goods;



    public Cart(List<OrderItem> orderItems,int amount, Goods goods) {
        this.orderItems=orderItems;
        this.amount = amount;
        this.goods = goods;
    }

    public void addItem(Goods goods, int amount, int orderId){
        OrderItem orderItem=new OrderItem(goods, amount, orderId);
        orderItems.add(orderItem);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("item", orderItems);
    }

    public void editItem(int id){

    }

    public void deliteItem(int id){

    }

    public void getListOfItems(){

    }

    public int getSumm(){
        int result=0;
        for(OrderItem order: orderItems){
            result+=order.getPrice();
        }
        return result;
    }

    public void saveOrderToDB(){

    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
