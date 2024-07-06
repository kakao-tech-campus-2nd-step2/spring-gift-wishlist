package gift.services;


import gift.JWTUtil;
import gift.Member;
import gift.MemberDto;
import gift.classes.Exceptions.EmailAlreadyExistsException;
import gift.repositories.MemberRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JWTUtil jwtUtil;

    @Autowired
    public MemberService(MemberRepository memberRepository, JWTUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    public void register(MemberDto memberDto) throws EmailAlreadyExistsException {

        Member member = new Member(memberDto.getEmail(), memberDto.getPassword());
        Member existingMember = memberRepository.find(member);

        if (existingMember != null) {
            throw new EmailAlreadyExistsException();
        }
        memberRepository.register(member);
    }

    public String login(MemberDto memberDto) {

        Member member = new Member(memberDto.getEmail(), memberDto.getPassword());
        Member existingMember = memberRepository.find(member);

        if (existingMember == null) {
            throw new NoSuchElementException("Email is not exist. ");
        }

        String token = jwtUtil.createJwt(member.getEmail());
        return token;

    }

}
