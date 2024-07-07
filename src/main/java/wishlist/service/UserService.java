package wishlist.service;

import org.springframework.stereotype.Service;
import wishlist.exception.CustomException.UserNotFoundException;
import wishlist.model.user.User;
import wishlist.model.user.UserDTO;
import wishlist.model.user.UserForm;
import wishlist.repository.UserRepository;
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long insertUser(UserForm userForm) {
        return userRepository.insert(new User(0L,userForm.getEmail(), userForm.getPassWord()));
    }

    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return new UserDTO(user.getId(),user.getEmail(), user.getPassWord());
    }

    public boolean existsEmail(String email) {
        try {
            userRepository.findByEmail(email);
        } catch (UserNotFoundException e) {
            return false;
        }
        return true;
    }

    public boolean isPassWordMatch(UserForm userForm) {
        return userForm.getPassWord()
            .equals(userRepository.findByEmail(userForm.getEmail()).getPassWord());
    }

    public void deleteUser(String id) {
        userRepository.delete(id);
    }
}
