package gift.feat.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import gift.core.authorization.UserDetails;
import gift.core.jwt.JwtProvider;
import gift.feat.product.domain.Product;
import gift.feat.product.service.ProductService;
import gift.feat.user.User;
import gift.feat.user.UserDao;
import gift.feat.user.dto.LoginRequestDto;
import gift.feat.user.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserRestController {
	private final UserDao userDao;
	private final ProductService productService;
	private final JwtProvider jwtTokenProvider;

	// email과 패스워드를 입력하면, 해당 유저의 정보를 JWT access token으로 반환
	@PostMapping("/login")
	public String login(@RequestBody LoginRequestDto loginRequestDto) {
		User user = userDao.findByEmail(loginRequestDto.email());
		String token = jwtTokenProvider.generateToken(user.getId().toString(), user.getRole());
		return token;
	}

	@PostMapping("/signup")
	public void signup(@RequestBody SignupRequestDto signupRequestDto) {
		userDao.save(User.of(signupRequestDto.id(), signupRequestDto.email(), signupRequestDto.password(), signupRequestDto.role()));
	}
}
