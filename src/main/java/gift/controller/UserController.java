package gift.controller;

import gift.auth.JwtUtil;
import gift.constants.ResponseMsgConstants;
import gift.dto.JwtDTO;
import gift.dto.ResponseDTO;
import gift.dto.UserDTO;
import gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Validated
@RequestMapping("/login")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<ResponseDTO> signUp(@Validated UserDTO userDTO) {
        try {
            userService.signUp(userDTO);
        } catch (RuntimeException e) {

        }
        return new ResponseEntity<>(new ResponseDTO(false, ResponseMsgConstants.WELL_DONE_MESSAGE),
                HttpStatus.CREATED);
    }

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            String token = JwtUtil.generateToken(userDTO.email());
            return new ResponseEntity<>(new JwtDTO(token), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new ResponseDTO(true, ResponseMsgConstants.CRITICAL_ERROR_MESSAGE),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
