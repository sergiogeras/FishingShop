package fishingshop.controller;

import fishingshop.domain.customer.Customer;
import fishingshop.domain.user.User;
import fishingshop.service.CustomerService;
import fishingshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import sun.plugin.liveconnect.SecurityContextHelper;

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
    private User user;



    @Autowired
    private UserService userService;

    @PostConstruct
    public void init(){

        customer=new Customer();
    }

    public void saveCustomerData(){
        FacesContext context=FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("customer", customer);


    }


    public void setCurrentCustomer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()){
            user=userService.getUserByUsername(authentication.getName());
            customer=user.getCustomer();
        }

    }

    public void eraseCurrentCustomer(){
        customer=new Customer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
