package gift.controller;

import gift.dto.MemberDto;
import gift.entity.Member;
import gift.exception.UnauthorizedException;
import gift.service.MemberService;
import gift.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final ProductService productService; //

    @Autowired
    public MemberController(MemberService memberService, ProductService productService) {
        this.memberService = memberService;
        this.productService = productService;
    }

    @PostMapping("/register") // 생성된 회원 정보로 JWT 토큰을 생성 -> 응답으로 반환
    public ResponseEntity<Map<String, String>> register(@RequestBody MemberDto memberDto) {
        // 회원 가입 처리
        Member member = memberService.register(memberDto.getEmail(), memberDto.getPassword());
        // 입력받은 정보로 부터 토큰 생성
        String token = productService.generateToken(member);
        // 응답으로 사용자 정보 맵 반환
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody MemberDto memberDto) {
        // 로그인 처리
        Member member = memberService.login(memberDto.getEmail(), memberDto.getPassword());

        // 로그인 실패 시 UnauthorizedException 발생
        if (member == null) {
            throw new UnauthorizedException("로그인 실패 : 사용자 정보가 유효하지 않습니다.");
        }

        // 로그인 성공 시 JWT 생성
        String token = productService.generateToken(member);

        // 응답으로 토큰을 포함한 맵 반환
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }



}