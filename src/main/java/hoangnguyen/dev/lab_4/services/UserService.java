package hoangnguyen.dev.lab_4.services;

import hoangnguyen.dev.lab_4.models.User;
import hoangnguyen.dev.lab_4.repositories.UserRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(@NotNull User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalStateException("User with ID " +
                        user.getId() + " does not exist."));
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setBirthDay(user.getBirthDay());
        existingUser.setDeleted(false);
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }

}
