package no.ntnu.webdev.webproject7.user;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users;

    public UserService() {
        this.users = new LinkedList<>();
        this.initializeTestData();
    }

    private void initializeTestData() {
        this.users.addAll(Arrays.asList(
                new User("1", false, "mail1@example.com", "123"),
                new User("2", true, "mail2@example.com", "321")
        ));
    }

    public boolean addUser(User user) {
        // Guard condition
        if (user == null || this.getUserByID(user.getId()) != null) {
            return false;
        }
        return this.users.add(user);
    }

    public boolean removeUser(String id) {
        // Guard condition
        if (id == null) {
            return false;
        }
        return this.users.removeIf(user -> id.equals(user.getId()));
    }

    public boolean updateUser(User user) {
        // Guard condition
        if (user == null || this.getUserByID(user.getId()) == null) {
            return false;
        }
        final int index = this.users.indexOf(user);
        this.users.set(index, user);
        return true;
    }

    public User getUserByID(String id) {
        // Guard condition
        if (id == null) {
            return null;
        }
        return this.users.stream()
                .filter(user -> id.equals(user.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<User> getUsers() {
        return this.users;
    }
}
