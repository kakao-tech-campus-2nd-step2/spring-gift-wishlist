package gift.service;

import gift.dto.MemberDTO;
import gift.exception.AlreadyExistMemberException;
import gift.repository.MemberDAO;
import gift.util.JwtProvider;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDAO memberDAO;
    private final JwtProvider jwtProvider;

    @Autowired
    public MemberService(MemberDAO memberDAO, JwtProvider jwtProvider) {
        this.memberDAO = memberDAO;
        this.jwtProvider = jwtProvider;
    }

    public Map<String, String> register(MemberDTO memberDTO) {
        if (memberDAO.findMember(memberDTO.email()) != null) {
            throw new AlreadyExistMemberException();
        }
        memberDAO.register(memberDTO);
        return Map.of("token:", jwtProvider.createAccessToken(memberDTO));
    }
}
