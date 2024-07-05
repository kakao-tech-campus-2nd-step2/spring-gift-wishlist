package gift.controller;

import gift.dto.CommonResponse;
import gift.dto.UserRequestDto;
import gift.service.JwtUtil;
import gift.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/members")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> registerUser(@RequestBody UserRequestDto request){
        userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponse("유저가 성공적으로 생성되었습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String,String>> giveAccessToken(@RequestBody UserRequestDto request) {
        userService.authenticate(request.getPassword(), request.getEmail());
        String token = jwtUtil.generateToken(request.getEmail());
        HashMap<String,String> response = new HashMap<>();
        response.put("accessToken", token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }
}
