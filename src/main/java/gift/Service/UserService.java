package gift.Service;

import gift.Exception.LoginException;
import gift.Model.Role;
import gift.Model.User;
import gift.Model.UserInfo;
import gift.Model.UserInfoDAO;
import gift.Token.JwtToken;
import gift.Token.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Service
public class UserService {
        private final UserInfoDAO userInfoDAO;
        private final JwtTokenProvider jwtTokenProvider;

        public UserService(UserInfoDAO userInfoDAO, JwtTokenProvider jwtTokenProvider){
            this.userInfoDAO = userInfoDAO;
            this.jwtTokenProvider = jwtTokenProvider;
            //테스트옹 admin 추가
            userInfoDAO.insertUser(new UserInfo("admin", "1234", Role.ADMIN));
        }

        public JwtToken register(User user){
            if(userInfoDAO.selectUser(user.email()) == null){
                UserInfo userInfo = new UserInfo(user.email(), user.password(), Role.CONSUMER);
                userInfoDAO.insertUser(userInfo);
                return new JwtToken(jwtTokenProvider.createToken(userInfo));
            }

            throw new LoginException();
        }

        public JwtToken login(User user){
            UserInfo userInfo1 = userInfoDAO.selectUser(user.email());
            if(userInfo1.password().equals(user.password())){
                return new JwtToken(jwtTokenProvider.createToken(userInfo1));
            }

            throw new LoginException();
        }
}
