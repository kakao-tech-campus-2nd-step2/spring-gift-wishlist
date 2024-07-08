package gift.service;

import gift.dto.*;
import gift.exception.InvalidPasswordException;
import gift.repository.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<UserResponseDTO> getAllUsers() {

        return userDAO.findAll().stream().map((userInfo) -> new UserResponseDTO(
                userInfo.id(),
                userInfo.email()
        )).toList();
    }

    public static String hashPassword(String plainPw) {
        return BCrypt.hashpw(plainPw, BCrypt.gensalt());
    }

    public UserResponseDTO signUp(UserRequestDTO userRequestDTO) {
        String email = userRequestDTO.email();
        String encryptedPW = hashPassword(userRequestDTO.password());

        UserInfoDTO userInfoDTO = userDAO.create(new UserEncryptedDTO(email, encryptedPW));

        return new UserResponseDTO(
                userInfoDTO.id(),
                userInfoDTO.email()
        );
    }

    public UserResponseDTO login(UserRequestDTO userRequestDTO) throws InvalidPasswordException {
        UserInfoDTO userInfoDTO = userDAO.findUserByEmail(userRequestDTO.email());
        String encodedOriginalPw = userInfoDTO.encryptedPw();

        if (!BCrypt.checkpw(userRequestDTO.password(), encodedOriginalPw)) {
            throw new InvalidPasswordException("Invalid password");
        }

        return new UserResponseDTO(
                userInfoDTO.id(),
                userInfoDTO.email()
        );
    }

    public void deleteUser(long id) {
        userDAO.delete(id);
    }

    public void updatePw(long id, PwUpdateDTO pwUpdateDTO) {
        String encryptedPW = hashPassword(pwUpdateDTO.password());

        userDAO.updatePw(new EncryptedUpdateDTO(id, encryptedPW));
    }
}
