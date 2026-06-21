package taskmanagementsystem.service;

import taskmanagementsystem.model.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    private final Map<String, User> users;

    public UserService() {
        this.users = new ConcurrentHashMap<>();
    }

    public User createUser(String name, String email) {
        User user = new User(UUID.randomUUID().toString(), name, email);
        users.put(user.getId(), user);
        return user;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}
