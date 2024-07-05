package gift.member.service;

import gift.member.Member;
import gift.member.dto.MemberReqDto;
import gift.member.dto.MemberResDto;
import gift.member.exception.MemberAlreadyExistsByEmailException;
import gift.member.exception.MemberNotFoundByIdException;
import gift.member.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResDto> getMembers() {
        return memberRepository.findMembers().stream()
                .map(MemberResDto::new)
                .toList();
    }

    public MemberResDto getMember(Long memberId) {
        Member findMember = memberRepository.findMemberByIdOrThrow(memberId);
        return new MemberResDto(findMember);
    }

    public String getMemberPassword(Long memberId) {
        Member findMember = memberRepository.findMemberByIdOrThrow(memberId);
        return findMember.getPassword();
    }

    public MemberResDto addMember(MemberReqDto memberReqDto) {
        checkDuplicateEmail(memberReqDto.email());  // 중복되는 이메일이 있으면 예외 발생

        // 일반 사용자로 회원 가입
        // 관리자 계정은 데이터베이스에서 직접 추가
        Long memberId = memberRepository.addMember(memberReqDto, "USER");
        Member newMember = memberRepository.findMemberByIdOrThrow(memberId);
        return new MemberResDto(newMember);
    }

    public void updateMember(Long memberId, MemberReqDto memberReqDto) {
        validateMemberExistsById(memberId);
        memberRepository.updateMemberById(memberId, memberReqDto);
    }

    public void deleteMember(Long memberId) {
        validateMemberExistsById(memberId);
        memberRepository.deleteMemberById(memberId);
    }

    private void validateMemberExistsById(Long memberId) {
        boolean isExist = memberRepository.isMemberExistById(memberId);

        if (!isExist) {
            throw MemberNotFoundByIdException.EXCEPTION;
        }
    }

    public void checkDuplicateEmail(String email) {
        boolean isExist = memberRepository.isMemberExistByEmail(email);

        if (isExist) {
            throw MemberAlreadyExistsByEmailException.EXCEPTION;
        }
    }
}
