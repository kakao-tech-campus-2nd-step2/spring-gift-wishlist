package gift.service;

import gift.dto.MemberDTO;
import gift.model.LoginToken;

public interface MemberService {

    //회원가입을 진행한다.
    public void register(MemberDTO memberDTO);

    //로그인을 진행한다.
    public LoginToken login(MemberDTO memberDTO);

    //회원의 접근 권한을 확인한다.
    public boolean checkRole(LoginToken loginToken);

    MemberDTO getLoginUser(String token);
}
