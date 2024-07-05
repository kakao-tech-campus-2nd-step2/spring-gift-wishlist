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
}
