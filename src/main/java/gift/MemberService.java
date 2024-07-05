package gift;

import jakarta.validation.Valid;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    public String register(@Valid Member member) {
        Member existingMember = memberRepository.findMemberByEmail(member.getEmail());
        if (existingMember != null) {
            throw new DuplicateKeyException("이미 존재하는 이메일 입니다.");
        }
        memberRepository.saveMember(member);
        return jwtUtil.generateToken(member.getEmail());
    }

    public String login(@Valid Member member) {
        Member existingMember = memberRepository.findMemberByEmail(member.getEmail());
        if(existingMember == null) {
            return "NoEmail";
        }
        if(!existingMember.getPassword().equals(member.getPassword())){
            return "inValidPassword";
        }
        return jwtUtil.generateToken(existingMember.getEmail());
    }
}
