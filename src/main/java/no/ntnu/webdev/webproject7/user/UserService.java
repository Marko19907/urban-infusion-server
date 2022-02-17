package no.ntnu.webdev.webproject7.user;

import no.ntnu.webdev.webproject7.utilities.Utilities;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

        //userRepository.save(new User("1", false, "mail1@example.com", "123"));
        //userRepository.save(new User("2", true, "mail2@example.com", "321"));

        //this.initializeTestData();
    }

    private void initializeTestData() {
        Arrays.asList(
                new User("1", true, "mail1@example.com", "123"),
                new User("2", false, "mail2@example.com", "321")
        ).forEach(this::addUser);
    }

    public boolean addUser(User user) {
        // Guard condition
        if (user == null || this.getUserByID(user.getId()) != null) {
            return false;
        }
        return this.userRepository.save(user).equals(user);
    }

    public boolean removeUser(String id) {
        // Guard condition
        if (id == null) {
            return false;
        }
        this.userRepository.deleteById(id);
        return this.getUserByID(id) == null;
    }

    public boolean updateUser(User user) {
        // Guard condition
        if (user == null || this.getUserByID(user.getId()) == null) {
            return false;
        }
        this.userRepository.save(user);
        return true;
    }

    public User getUserByID(String id) {
        // Guard condition
        if (id == null) {
            return null;
        }
        Optional<User> result = this.userRepository.findById(id);
        return result.orElse(null);
    }

    public List<User> getUsers() {
        return Utilities.iterableToList(this.userRepository.findAll());
    }
}
