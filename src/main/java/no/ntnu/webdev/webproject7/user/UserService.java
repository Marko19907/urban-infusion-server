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
    }

    public List<UserEntity> getAllUsers() {
        return Utilities.iterableToList(this.userRepository.findAll());
    }

    public UserEntity getUserById(String id) {
        // Guard condition
        if (id == null) {
            return null;
        }
        Optional<UserEntity> result = this.userRepository.findById(id);
        return result.orElse(null);
    }

    public boolean addUser(UserEntity userEntity) {
        if (userEntity == null) {
            return false;
        }
        return this.userRepository.save(userEntity).equals(userEntity);
    }

    public boolean updateUser(UserEntity userEntity) {
        if (userEntity == null) {
            return false;
        }
        this.userRepository.save(userEntity);
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
