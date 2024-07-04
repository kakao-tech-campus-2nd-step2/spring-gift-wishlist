package gift.auth;

import gift.user.UserDao;
import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(userDao.selectUser(email)==null){
            return null;
        }
        String storedPassword = userDao.selectUser(email).getPassword();
        return new org.springframework.security.core.userdetails.User(
            userDao.selectUser(email).getEmail(),
            userDao.selectUser(email).getPassword(),
            Collections.emptyList());
    }
}
