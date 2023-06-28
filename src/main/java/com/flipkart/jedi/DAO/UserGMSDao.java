package com.flipkart.jedi.DAO;
import com.flipkart.jedi.bean.User;
import java.util.List;

public interface UserGMSDao {
    void createUser(User user);
    User getUserByUsername(String userId);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> getAllUsers();
    boolean authenticateUser(String userId, String password);
}
