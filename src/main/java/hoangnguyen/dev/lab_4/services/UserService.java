package hoangnguyen.dev.lab_4.services;

import hoangnguyen.dev.lab_4.models.User;
import hoangnguyen.dev.lab_4.repositories.UserRepository;
import hoangnguyen.dev.lab_4.requests.UserEdit;
import hoangnguyen.dev.lab_4.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(long id){
        return userRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Không tìm thấy user có id= " +id);
                }
        );
    }

    public User createUser(UserRequest userRequest){
        try{
            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthDay(dateFormat.parse(String.valueOf(userRequest.getBirthDay())));
            user.setDeleted(false);
            user.setRole(userRequest.getRole());
            userRepository.save(user);
            return user;
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
    public User updateUser(UserEdit userEdit){
        try{
            User user = getUserById(userEdit.getId());
            user.setUsername(userEdit.getUsername());
            user.setEmail(userEdit.getEmail());
            user.setFirstName(userEdit.getFirstName());
            user.setLastName(userEdit.getLastName());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthDay(dateFormat.parse(String.valueOf(userEdit.getBirthDay())));
            user.setDeleted(false);
            user.setRole(userEdit.getRole());
            userRepository.save(user);
            return user;
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
//    public void deleteUser(UserEdit userEdit){
//        User user = getUserById(userEdit.getId());
//
//    }
}
