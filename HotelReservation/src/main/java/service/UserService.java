package service;

import java.util.ArrayList;
import java.util.List;
import models.User;

public class UserService {
private List<User> users;

public UserService() {
   users = new ArrayList<>();
}

public void addUser(int userId, String name, String email) {
   User user = new User(userId, name, email);
   users.add(user);
}

public User getUserById(int userId) {
   for (User user : users) {
       if (user.getUserId() == userId) {
           return user;
       }
   }
   return null;
}

public List<User> getAllUsers() {
   return users;
}
}