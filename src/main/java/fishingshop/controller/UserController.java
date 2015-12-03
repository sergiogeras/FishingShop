package fishingshop.controller;

import fishingshop.domain.customer.Customer;
import fishingshop.domain.user.User;
import fishingshop.service.CustomerService;
import fishingshop.service.UserService;
import fishingshop.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Created by Сергей on 09.11.2015.
 */

@Controller
@Scope("session")
public class UserController implements Serializable {

    private Customer customer;
    private User user;
    private ResourceBundle bundle;


    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MailSender mailSender;

    @PostConstruct
    public void init(){
        bundle= ResourceBundle.getBundle("locales.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        user=new User();
        user.setCustomer(new Customer());
        customer=new Customer();
    }

    @Transactional
    public String registerUser(){
        mailSender.sendRegistrationMessage(user);
        userService.addUser(user);
        user=new User();
        return "/public/register-success.xhtml?faces-redirect=true";
    }

    public void saveCustomerData(){
        FacesContext context=FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("customer", customer);
    }

    

    public void updateCustomerData(){

        customerService.editCustomer(customer);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("successfully_updated")));
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
