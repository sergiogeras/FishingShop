package fishingshop.domain.user;

import fishingshop.domain.customer.Customer;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Сергей on 09.11.2015.
 */

@Entity
@Table (name="user")
public class User implements Serializable {

    @Id
    @Column(name= "USERNAME", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name= "PASSWORD", nullable = false, length = 60)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    private Role role;

    public User(){

    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
