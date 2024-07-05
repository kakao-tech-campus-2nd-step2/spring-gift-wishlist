package gift.service;

import gift.dto.MemberDTO;
import gift.dto.MemberPasswordDTO;
import gift.exception.AlreadyExistMemberException;
import gift.exception.InvalidNewPasswordException;
import gift.exception.InvalidPasswordException;
import gift.exception.NoSuchMemberException;
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

    public Map<String, String> login(MemberDTO memberDTO) {
        MemberDTO findedmemberDTO = memberDAO.findMember(memberDTO.email());
        if (findedmemberDTO == null) {
            throw new NoSuchMemberException();
        }
        if (!memberDTO.password().equals(findedmemberDTO.password())) {
            throw new InvalidPasswordException();
        }
        return Map.of("token:", jwtProvider.createAccessToken(memberDTO));
    }

    public Map<String, String> changePassword(String accessToken, MemberPasswordDTO memberPasswordDTO) {
        var email = jwtProvider.parseAccessToken(accessToken);
        MemberDTO findedmemberDTO = memberDAO.findMember(email);
        if (findedmemberDTO == null) {
            throw new NoSuchMemberException();
        }
        if (!memberPasswordDTO.password().equals(findedmemberDTO.password())) {
            throw new InvalidPasswordException();
        }
        if (!memberPasswordDTO.newPassword1().equals(memberPasswordDTO.newPassword2())) {
            throw new InvalidNewPasswordException();
        }
        MemberDTO updatedMemberDTO = new MemberDTO(email, memberPasswordDTO.newPassword1());
        memberDAO.changePassword(updatedMemberDTO);
        return Map.of("token:", jwtProvider.createAccessToken(updatedMemberDTO));
    }
}
