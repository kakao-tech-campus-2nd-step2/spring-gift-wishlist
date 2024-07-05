package gift.service;

import gift.dto.UserResponseDTO;
import gift.repository.UserDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userDAO.findAll();
    }
}
