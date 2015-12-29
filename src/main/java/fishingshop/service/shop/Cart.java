package fishingshop.service.shop;

import fishingshop.domain.goods.Goods;
import fishingshop.domain.order.OrderItem;
import fishingshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
    private ResourceBundle bundle;

    @Autowired
    OrderService orderService;


    public Cart() {
        orderItems=new ArrayList<>();
    }

    public Cart(List<OrderItem> orderItems,int amount, Goods goods) {
        this.orderItems=orderItems;
        this.amount = amount;
        this.goods = goods;

    }

    @PostConstruct
    public void init(){
        bundle= ResourceBundle.getBundle("locales.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    public void addItem(Goods goods, int amount, int orderId){
        if(goods.getGoodsAmount() >= amount){     //Are there enough goods in the store?
            OrderItem orderItem=new OrderItem(goods, amount, orderId);

            boolean exists=false; //Checking for duplicates
            for(OrderItem item: orderItems ){
                if(item.getGoods().getId()==goods.getId()){
                    item.setAmount(item.getAmount()+amount);
                    exists=true;
                    break;
                }
            }

            if(orderItems.isEmpty() || !exists) {
                orderItems.add(orderItem);

            }
            exists=false;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("items", orderItems);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("added_to_the_cart")));
        }   else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("empty_stock")));
        }

    }

    public void editItems(Map<Integer, Integer> positionAmount){

        //Changing amount of goods
        for(Map.Entry<Integer, Integer> entry: positionAmount.entrySet()){
            orderItems.get(entry.getKey()).setAmount(entry.getValue());
        }

        //Deleting goods with zero values of amount
        for(OrderItem orderItem: orderItems){
            if(orderItem.getAmount()==0){
                orderItems.remove(orderItem);
            }
        }

    }

    public void deleteItem(int id){
        orderItems.remove(id);
    }


    public int getSumm(){
        int result=0;
        for(OrderItem order: orderItems){
            result+=order.getPrice();
        }
        return result;
    }

    public int getAmount(){
        return orderItems.size();
    }

    public void saveOrderToDB(){
        orderService.addOrder(orderItems);
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
