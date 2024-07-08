package gift.service;

import gift.dto.MemberDTO;
import gift.model.LoginToken;

public interface MemberService {

    void register(MemberDTO memberDTO);

    LoginToken login(MemberDTO memberDTO);

    boolean checkRole(LoginToken loginToken);

    MemberDTO getLoginUser(String token);
}
