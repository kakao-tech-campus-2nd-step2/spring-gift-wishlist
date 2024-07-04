package gift.service;

import gift.dto.UserResponseDTO;
import gift.entity.User;
import gift.entity.UserDao;
import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import gift.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDao userDao;
    private final TokenService tokenService;

    public UserService(UserDao userDao, TokenService tokenService) {
        this.userDao = userDao;
        this.tokenService = tokenService;
    }

    public UserResponseDTO registerUser(String email, String password) {
        Optional<User> existingUser = userDao.selectUserByEmail(email);
        if (existingUser.isPresent()) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        }

        User user = new User(null, email, password);
        Long userId = userDao.insertUser(user);
        return new UserResponseDTO(userId, email);
    }

    public String loginUser(String email, String password) {
        User user = userDao.selectUserByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_CREDENTIALS, HttpStatus.FORBIDDEN));

        if (!user.password.equals(password)) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS, HttpStatus.FORBIDDEN);
        }

        return tokenService.generateToken(user.email);
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userDao.selectAllUsers();
        return users.stream()
                .map(UserMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userDao.selectUserById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        return UserMapper.toUserResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long id, String email, String password) {
        User existingUser = userDao.selectUserById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

        User updatedUser = new User(id, email, password);
        userDao.updateUser(updatedUser);
        return UserMapper.toUserResponseDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        userDao.selectUserById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        userDao.deleteUser(id);
    }
}
