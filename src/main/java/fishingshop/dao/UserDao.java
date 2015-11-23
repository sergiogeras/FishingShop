package fishingshop.dao;

import fishingshop.domain.customer.Customer;
import fishingshop.domain.user.User;

import java.util.List;

/**
 * Created by Сергей on 09.11.2015.
 */
public interface UserDao {

    void addUser(User user);
    void editUser(User user);
    User getUserByUsername(String username);
    List<User> getAllUsers();
}
