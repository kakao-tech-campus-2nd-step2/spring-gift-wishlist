package gift.controller;

import gift.dto.UserResponseDTO;
import gift.repository.UserDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getUsers() {
        return userDAO.findAll();
    }
}
