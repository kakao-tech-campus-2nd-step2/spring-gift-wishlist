package wishlist.service;

import org.springframework.stereotype.Service;
import wishlist.exception.CustomException.UserNotFoundException;
import wishlist.model.user.User;
import wishlist.model.user.UserDTO;
import wishlist.repository.UserRepository;
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void insertUser(UserDTO userDTO) {
        userRepository.insert(new User(userDTO.getEmail(), userDTO.getPassWord()));
    }

    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return new UserDTO(user.getEmail(), user.getPassWord());
    }

    public boolean existsEmail(String email) {
        try {
            userRepository.findByEmail(email);
        } catch (UserNotFoundException e) {
            return false;
        }
        return true;
    }

    public boolean isPassWordMatch(UserDTO userDTO) {
        return userDTO.getPassWord()
            .equals(userRepository.findByEmail(userDTO.getEmail()).getPassWord());
    }

    public void deleteUser(String id) {
        userRepository.delete(id);
    }
}
