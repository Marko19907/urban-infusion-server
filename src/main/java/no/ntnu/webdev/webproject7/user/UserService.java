package no.ntnu.webdev.webproject7.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static no.ntnu.webdev.webproject7.utilities.UtilitiesKt.iterableToList;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return iterableToList(this.userRepository.findAll());
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
        UserEntity saved = this.userRepository.save(userEntity);
        return Objects.equals(userEntity.getId(), saved.getId());
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
