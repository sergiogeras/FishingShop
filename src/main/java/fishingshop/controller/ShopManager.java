package fishingshop.controller;

import fishingshop.domain.goods.Goods;
import fishingshop.domain.order.Delivery;
import fishingshop.domain.order.OrderItem;
import fishingshop.domain.order.Orders;
import fishingshop.domain.order.Payment;
import fishingshop.service.DeliveryService;
import fishingshop.service.GoodsService;
import fishingshop.service.OrderService;
import fishingshop.service.PaymentService;
import fishingshop.service.shop.Cart;
import fishingshop.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

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
    private int deliveryIndex;
    private int paymentIndex;
    private Delivery delivery;
    private Payment payment;
    private String note;


    @Autowired
    private Cart cart;

    @Autowired
    private OrderService orderService;


    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MailSender mailSender;

    @PostConstruct
    public void init(){
        tempOrderId = 1;
        amount = 1;
        totalSum = cart.getSumm();
        totalAmount = cart.getAmount();
        deliveryList = deliveryService.getAllDeliveries();
        paymentList = paymentService.getAllPayments();
        deliveryIndex = 1;
        paymentIndex = 1;

    }


    public ShopManager() {

    }

    public void saveDeliveryPaymentData(){
        FacesContext context=FacesContext.getCurrentInstance();
        delivery = deliveryList.get(deliveryIndex -1);
        payment = paymentList.get(paymentIndex -1);
        context.getExternalContext().getSessionMap().put("delivery", delivery );
        context.getExternalContext().getSessionMap().put("payment", payment );
        context.getExternalContext().getSessionMap().put("note", note);
    }

    public void addGoodsToTheCart(Goods goods){
        cart.addItem(goods, amount, tempOrderId);
        amount = 1;
    }




    public void makeOrder(){
        cart.saveOrderToDB();
    }

    public void changeOrder(){
        cart.editItems(positionsAndAmount);
    }

    public void deleteItem(int id){
        cart.deleteItem(goodsPositionId);
    }

    public List<OrderItem> getOrderItems(){
        return cart.getOrderItems();
    }


    @Transactional
    public void buyGoods(){

        mailSender.sendOrderMessage(cart.getOrderItems(), cart.getSumm(), orderService.generateOrderId());
        cart.saveOrderToDB();

    }

    public List<Integer> getAmountList(Goods goods) {
        return goodsService.getAmountList(goods);
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

    public int getDeliveryIndex() {
        return deliveryIndex;
    }

    public void setDeliveryIndex(int deliveryIndex) {
        this.deliveryIndex = deliveryIndex;
    }

    public int getPaymentIndex() {
        return paymentIndex;
    }

    public void setPaymentIndex(int paymentIndex) {
        this.paymentIndex = paymentIndex;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
