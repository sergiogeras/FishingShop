package fishingshop.controller;

import fishingshop.domain.customer.Customer;
import fishingshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by Сергей on 09.11.2015.
 */

@Controller
@Scope("session")
public class UserController implements Serializable {

    private Customer customer;

    @Autowired
    CustomerService customerService;

    @PostConstruct
    public void init(){
        customer=new Customer();
    }

    public void saveCustomerData(){
        FacesContext context=FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("customer", customer);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
