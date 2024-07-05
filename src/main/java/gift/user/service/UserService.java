package gift.user.service;

import gift.user.dto.TokenDto;
import gift.user.dto.UserDto;
import gift.user.entity.UserEntity;
import gift.user.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// UserController로부터 입력을 받아서 엔터티를 사용해서 비즈니스 로직 수행
@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // 회원가입 비즈니스 로직 처리
    public TokenDto register(UserDto userDto) {
         UserEntity userEntity = userDao.insertUser(userDto);

         // 반환값은 주어진 정보를 토대로 생성한 토큰
         return userEntity.getToken();
    }

    // 로그인 비즈니스 로직 처리
    public TokenDto login(UserDto userDto) {
        UserEntity userEntity = userDao.selectUser(userDto.email());

        // 비밀번호 검증
        userEntity.verifyPassword(userDto.password());

        // 비밀번호 검증이 완료되면 토큰 발급
        return userEntity.getToken();
    }
}
