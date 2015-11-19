package fishingshop.controller;

import fishingshop.domain.goods.Goods;
import fishingshop.domain.order.Delivery;
import fishingshop.domain.order.OrderItem;
import fishingshop.domain.order.Payment;
import fishingshop.service.DeliveryService;
import fishingshop.service.OrderService;
import fishingshop.service.PaymentService;
import fishingshop.service.shop.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Class for interaction with the customer
 */

@Controller
@Scope("session")
public class ShopManager implements Serializable {

    private Goods goods;
    private int amount;
    private int tempOrderId;
    private int goodsPositionId;
    private Map<Integer, Integer> positionsAndAmount;   //Map with index and amount of goods for editing order
    private int totalSum;
    private int totalAmount;
    private int orderId;
    List<OrderItem> orderItems;
    private List<Delivery> deliveryList;
    private List<Payment> paymentList;
    private Payment payment;
    private Delivery delivery;
    private int deliveryNumber;
    private int paymentNumber;


    @Autowired
    private Cart cart;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private PaymentService paymentService;

    @PostConstruct
    public void init(){
        tempOrderId =1;
        amount=1;
        totalSum=cart.getSumm();
        totalAmount=cart.getAmount();
        deliveryList=deliveryService.getAllDeliveries();
        paymentList=paymentService.getAllPayments();
        delivery=deliveryList.get(0);
        payment=paymentList.get(0);

    }


    public ShopManager() {

    }

    public void saveDeliveryPaymentData(){
        FacesContext context=FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("delivery", deliveryList.get(deliveryNumber-1) );
        context.getExternalContext().getSessionMap().put("payment", paymentList.get(paymentNumber-1) );
    }

    public void addGoodsToTheCart(Goods goods){
        cart.addItem(goods, amount, tempOrderId);
        amount=1;
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

    public List<OrderItem> getOrderItems(){
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

    public int getTempOrderId() {
        return tempOrderId;
    }

    public void setTempOrderId(int tempOrderId) {
        this.tempOrderId = tempOrderId;
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

    public int getTotalSum() {
        return  cart.getSumm();
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public int getTotalAmount() {
        return cart.getAmount();
    }

    public int getOrderId(){
        return orderService.getOrderId();
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Delivery> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public int getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(int paymentNumber) {
        this.paymentNumber = paymentNumber;
    }
}
