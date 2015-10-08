package fishingshop.controller;

import fishingshop.domain.goods.Goods;
import fishingshop.service.shop.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Class for interaction with the customer
 */

@Controller
@Scope("session")
public class ShopManager {

    private Goods goods;
    private int amount;
    private int orderId;

    @Autowired
    private Cart cart;

    public void addGoodsToTheCart(){
        cart.addItem(goods, amount, orderId);
    }

    public void makeOrder(){
        cart.saveOrderToDB();
    }

    public void changeOrder(){

    }

    public void deleteItem(){

    }

    public void showListOfGoods(){

    }

    public void buyGoods(){
        cart.saveOrderToDB();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
