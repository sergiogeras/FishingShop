package fishingshop.controller;

import fishingshop.domain.goods.Goods;
import fishingshop.domain.order.OrderItem;
import fishingshop.service.shop.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * Class for interaction with the customer
 */

@Controller
@Scope("session")
public class ShopManager {

    private Goods goods;
    private int amount;
    private int orderId;
    private int goodsPositionId;
    private Map<Integer, Integer> positionsAndAmount;   //Map with index and amount of goods for editing order

    @Autowired
    private Cart cart;

    public void addGoodsToTheCart(){
        cart.addItem(goods, amount, orderId);
    }

    public void makeOrder(){
        cart.saveOrderToDB();
    }

    public void changeOrder(){
        cart.editItems(positionsAndAmount);
    }

    public void deleteItem(){
        cart.deleteItem(goodsPositionId);
    }

    public List<OrderItem> showListOfGoods(){
        return cart.getOrderItems();
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

    public int getGoodsPositionId() {
        return goodsPositionId;
    }

    public void setGoodsPositionId(int goodsPositionId) {
        this.goodsPositionId = goodsPositionId;
    }

    public Map<Integer, Integer> getPositionsAndAmount() {
        return positionsAndAmount;
    }

    public void setPositionsAndAmount(Map<Integer, Integer> positionsAndAmount) {
        this.positionsAndAmount = positionsAndAmount;
    }
}
