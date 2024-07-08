package gift.service;

import gift.database.JdbcMemeberRepository;
import gift.dto.MemberDTO;
import gift.exceptionAdvisor.MemberServiceException;
import gift.model.LoginToken;
import gift.model.Member;
import gift.model.MemberRole;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private JdbcMemeberRepository jdbcMemeberRepository;

    public MemberServiceImpl(JdbcMemeberRepository jdbcMemeberRepository) {
        this.jdbcMemeberRepository = jdbcMemeberRepository;
    }

    @Override
    public void register(MemberDTO memberDTO) {
        if (checkEmailDuplication(memberDTO.getEmail())) {
            throw new MemberServiceException("이메일이 중복됩니다", HttpStatus.FORBIDDEN);
        }

        jdbcMemeberRepository.create(memberDTO.getEmail(), memberDTO.getPassword(),
            MemberRole.COMMON_MEMBER.toString());
    }

    @Override
    public LoginToken login(MemberDTO memberDTO) {
        Member member = findByEmail(memberDTO.getEmail());

        if (memberDTO.getPassword().equals(member.getPassword())) {
            LoginToken loginToken = new LoginToken(member.getId(), member.getEmail(),
                member.getRole());
            member.setToken(loginToken.getToken());
            jdbcMemeberRepository.update(member.getId(), member);
            return loginToken;
        }

        throw new MemberServiceException("잘못된 로그인 시도입니다.", HttpStatus.FORBIDDEN);
    }

    @Override
    public boolean checkRole(LoginToken loginToken) {
        return false;
    }

    @Override
    public MemberDTO getLoginUser(String token) {
        Member member = jdbcMemeberRepository.findByToken(token);

        return new MemberDTO(member.getEmail(), null, member.getRole());
    }


    private boolean checkEmailDuplication(String email) {
        try {
            jdbcMemeberRepository.findByEmail(email);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    private Member findByEmail(String email) {
        try {
            return jdbcMemeberRepository.findByEmail(email);
        } catch (EmptyResultDataAccessException e) {
            throw new MemberServiceException("잘못된 로그인 시도입니다.", HttpStatus.FORBIDDEN);
        }
    }
}
