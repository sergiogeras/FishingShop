package fishingshop.controller;

import fishingshop.domain.order.Orders;
import fishingshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Сергей on 03.12.2015.
 */

@Controller
@Scope("request")
public class UserTableController implements Serializable {


    private List<Orders> ordersList;
    private int total;

    @Autowired
    private UserController userController;

    @Autowired
    private OrderService orderService;

    @PostConstruct
    public void init(){
        ordersList = orderService.getOrdersByCustomer(userController.getCustomer());
        for(Orders orders : ordersList){
            total+=orders.getCost();
        }
    }


    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public int getTotal() {
        return total;
    }
}
