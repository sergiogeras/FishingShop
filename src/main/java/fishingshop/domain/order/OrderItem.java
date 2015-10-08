package fishingshop.domain.order;

import fishingshop.domain.goods.Goods;
import org.springframework.stereotype.Component;

/**
 * Goods and amount
 */


public class OrderItem {

    private Goods goods;
    private int amount;
    private int orderId;

    public OrderItem(){

    }

    public OrderItem(Goods goods, int amount, int orderId) {
        this.goods = goods;
        this.amount = amount;
        this.orderId=orderId;
    }

    public int getPrice(){
        return goods.getPrice()*amount;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
