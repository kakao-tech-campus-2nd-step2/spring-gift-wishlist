package gift.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserInfoService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 실제로는 데이터베이스에서 사용자 정보를 가져와야 합니다.
        // 여기서는 예제로 하드코딩된 사용자 정보를 반환합니다.
        if ("admin@email.com".equals(email)) {
            return new User("admin@email.com", "$2a$10$DowQnFj8rR5f4QO2T8xpeO/ZJf/9VoI8EQU/hyRksL3ePZT/Og1yO", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}
