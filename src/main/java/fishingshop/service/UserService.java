package fishingshop.service;

import fishingshop.domain.user.User;

import java.util.List;

/**
 * Created by Сергей on 09.11.2015.
 */
public interface UserService {

    void addUser(User user);
    void editUser(User user);
    User getUserByUsername(String username);
    List<User> getAllUsers();
}
