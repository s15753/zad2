package repository;

import domain.User;

import java.util.List;

public interface Repository {
    User getUserByName(String name);
    void addUser(User user);
    List<User> getAllUsers();
    void addPremiumPrivilege(String username);
}
