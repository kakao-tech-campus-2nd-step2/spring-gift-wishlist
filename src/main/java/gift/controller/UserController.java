package gift.controller;

import gift.DTO.Token;
import gift.DTO.UserDTO;
import gift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    /*
     * 로그인
     * 입력 받은 user의 email과 DB 내의 user의 email로 생성한 token을 비교
     * token 값이 같다면 : 200 OK 및 token 반환
     * token 값이 다르다면 : 401 UnAuthorized 및 token 반환
     */
    @PostMapping("/login")
    public ResponseEntity<Token> checkToken(@RequestBody UserDTO user){
        Token token = userService.makeToken(user);

        UserDTO savedUser = userService.loadOneUser(user.getEmail());
        Token savedToken = userService.makeToken(savedUser);

        if(token.getToken().equals(savedToken.getToken()))
            return new ResponseEntity<>(token, HttpStatus.OK);

        return new ResponseEntity<>(token, HttpStatus.UNAUTHORIZED);
    }
    /*
     * 회원가입
     * 회원가입 성공시 : 201 Created 및 Token 반환
     */
    @PostMapping("/register")
    public ResponseEntity<Token> giveToken(@RequestBody UserDTO user){
        userService.createUser(user);
        Token token = userService.makeToken(user);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
}
