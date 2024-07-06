package gift;

import jakarta.validation.Valid;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String register(@Valid Member member) {
        Member existingMember = memberRepository.findMemberByEmail(member.getEmail());
        if (existingMember != null) {
            throw new DuplicateKeyException("이미 존재하는 이메일 입니다.");
        }
        Member savedMember = memberRepository.saveMember(member);
        return JwtUtil.generateToken(savedMember.getEmail());
    }

    public String login(@Valid Member member) {
        Member existingMember = memberRepository.findMemberByEmail(member.getEmail());
        if(existingMember == null) {
            return "NoEmail";
        }
        if(!existingMember.getPassword().equals(member.getPassword())){
            return "inValidPassword";
        }
        return JwtUtil.generateToken(existingMember.getEmail());
    }
}
