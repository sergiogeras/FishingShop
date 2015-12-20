package fishingshop.controller;

import fishingshop.domain.customer.Customer;
import fishingshop.domain.order.Orders;
import fishingshop.domain.order.Status;
import fishingshop.service.OrderService;
import fishingshop.service.StatusService;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Scope("session")
public class OrderAdminController implements Serializable {

    private List<Orders> orderItemList;

    private List<Orders> customerOrderList;

    private int statusIndex;

    private int totalSum;

    private List<Status> statusList;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StatusService statusService;

    @PostConstruct
    public void init(){
        statusList = statusService.getAllStatuses();
        orderItemList = orderService.getAllOrders();
    }

    public void showOrderDetails(Customer customer, int orderId){
        totalSum = 0;
        Map<String, Object> props=new HashMap<>();
        props.put("contentWidth", 800);
        customerOrderList = orderService.getOrderDetailsByCustomer(customer, orderId);
        countTotalSum(customerOrderList);


        statusIndex = customerOrderList.get(0).getStatus().getId();
        RequestContext.getCurrentInstance().openDialog("orderPage", props, null);
    }

    public void changeOrderStatus(){
        orderService.editOrders(customerOrderList, statusList.get(statusIndex-1));
        RequestContext.getCurrentInstance().closeDialog(0);

    }

    public void updateTable(){
        orderItemList = orderService.getAllOrders();
    }

    public void countTotalSum(List<Orders> customerOrder){
        for(Orders o: customerOrder){
            totalSum+=o.getCost();
        }
    }

    public List<Orders> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setCustomerOrderList(List<Orders> customerOrderList) {
        this.customerOrderList = customerOrderList;
    }

    public List<Orders> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<Orders> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public int getStatusIndex() {
        return statusIndex;
    }

    public void setStatusIndex(int statusIndex) {
        this.statusIndex = statusIndex;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }
}
