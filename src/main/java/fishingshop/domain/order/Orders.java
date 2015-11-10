package fishingshop.domain.order;

import fishingshop.domain.customer.Customer;
import fishingshop.domain.goods.Goods;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;



@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column
    private Integer amount;

    @Column
    private Integer cost;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @Column
    private String note;

    @Column
    private  String status;

    @ManyToOne
    @JoinColumn(name = "GOODS_ID")
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    public Orders(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
