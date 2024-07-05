package gift.service;

import gift.domain.Member;
import gift.dto.request.MemberRequestDto;
import gift.dto.response.MemberResponseDto;
import gift.exception.EmailDuplicationException;
import gift.exception.MemberNotFoundException;
import gift.repository.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void memberJoin(MemberRequestDto memberRequestDto){
        Member member = Member.toEntity(memberRequestDto);

        Optional<Member> memberByEmail = memberRepository.findMemberByEmail(memberRequestDto.email());

        if(memberByEmail.isPresent()){
            throw new EmailDuplicationException();
        }

        memberRepository.memberSave(member);
    }

    public MemberResponseDto findOneByEmailAndPassword(MemberRequestDto memberRequestDto){
        Member findMember = memberRepository.findMemberByEmailAndPassword(memberRequestDto.email(), memberRequestDto.password())
                .orElseThrow(MemberNotFoundException::new);

        return MemberResponseDto.from(findMember);
    }

}
