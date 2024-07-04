package gift.product.service;

import gift.product.dto.MemberDto;
import gift.product.model.Member;
import gift.product.repository.AuthRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void register(MemberDto memberDto) {
        validateMemberAlreadyExist(memberDto);

        Member member = new Member(memberDto.email(), memberDto.password());
        authRepository.registerMember(member);
    }

    private void validateMemberAlreadyExist(MemberDto memberDto) {
        boolean isMemberExist = authRepository.existsByEmail(memberDto.email());

        if (isMemberExist) {
            throw new IllegalArgumentException("이미 회원으로 등록된 이메일입니다.");
        }
    }
}
