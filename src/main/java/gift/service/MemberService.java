package gift.service;

import gift.exception.ForbiddenException;
import gift.model.Member;
import gift.repository.MemberRepository;
import gift.security.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder,
        JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String register(String email, String password) {
        if (memberRepository.findByEmail(email) != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Email already exists");
        }
        Member member = new Member(email, passwordEncoder.encode(password));
        memberRepository.save(member);
        return jwtUtil.generateToken(member);
    }

    public String login(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new ForbiddenException("User not found");
        }
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new ForbiddenException("Invalid password");
        }
        return jwtUtil.generateToken(member);
    }


    public Member authenticate(String email, String password) {
        // 이메일을 사용하여 데이터베이스에서 사용자 정보를 조회합니다.
        Member member = memberRepository.findByEmail(email);
        // 사용자가 존재하고, 비밀번호가 일치하는지 확인합니다.
        if (member != null && member.getPassword().equals(password)) {
            return member; // 인증 성공 시 Member 객체 반환
        } else {
            return null; // 인증 실패 시 null 반환
        }
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}

