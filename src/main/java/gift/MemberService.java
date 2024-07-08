package gift;

import jakarta.validation.Valid;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String register(@Valid Member member) {
        memberRepository.findMemberByEmail(member.getEmail())
                .ifPresent(existingMember -> {
                    throw new DuplicateKeyException("이미 존재하는 이메일 입니다.");
                });
        Member savedMember = memberRepository.saveMember(member);
        return JwtUtil.generateToken(savedMember.getEmail());
    }

    public String login(Member member) {
        Member existingMember = memberRepository.findMemberByEmail(member.getEmail())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 이메일 또는 잘못된 비밀번호 입니다."));
        if (!existingMember.getPassword().equals(member.getPassword())) {
            throw new NoSuchElementException("존재하지 않는 이메일 또는 잘못된 비밀번호 입니다.");
        }
        return JwtUtil.generateToken(existingMember.getEmail());
    }
}
