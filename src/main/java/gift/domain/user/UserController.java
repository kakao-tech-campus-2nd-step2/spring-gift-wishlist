package gift.domain.user;

import gift.global.response.ResponseMaker;
import gift.global.response.SimpleResultResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입
     * @param userDTO 가입 시 입력 정보
     * @return 응답 객체
     */
    @PostMapping("/users")
    public ResponseEntity<SimpleResultResponseDto> join(@Valid @ModelAttribute UserDTO userDTO) {
        userService.join(userDTO);

        return ResponseMaker.createSimpleResponse(HttpStatus.OK, "회원 가입에 성공했습니다");
    }


    /**
     * 회원 로그인
     * @param userDTO 로그인 입력 정보
     * @return jwt 담은 응답 객체
     */
    @PostMapping("/users/login")
    public ResponseEntity<SimpleResultResponseDto> login(@Valid @ModelAttribute UserDTO userDTO) {
        String jwt = userService.login(userDTO);

        return ResponseMaker.createSimpleResponseWithJwtOnHeader(HttpStatus.OK, "로그인에 성공했습니다", jwt);
    }
}
