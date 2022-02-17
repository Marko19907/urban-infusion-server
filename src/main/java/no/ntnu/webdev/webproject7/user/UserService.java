package no.ntnu.webdev.webproject7.user;

import no.ntnu.webdev.webproject7.utilities.Utilities;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userRepository.save(new User("0", false, "some_email@gmail.com", "124"));
    }

    public List<User> getAllUsers() {
        return Utilities.iterableToList(this.userRepository.findAll());
    }

    public User getUserById(String id) {
        // Guard condition
        if (id == null) {
            return null;
        }
        Optional<User> result = this.userRepository.findById(id);
        return result.orElse(null);
    }

    public boolean addUser(User user) {
        if (user == null) {
            return false;
        }
        return this.userRepository.save(user).equals(user);
    }

    public boolean updateUser(User user) {
        if (user == null) {
            return false;
        }
        this.userRepository.save(user);
        return true;
    }

    public boolean deleteUser(String id) {
        if (id == null) {
            return false;
        }
        this.userRepository.deleteById(id);
        return this.getUserById(id) == null;
    }
}
