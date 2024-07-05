package gift.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<SiteUser> findAllUser(){
        return userRepository.findAll();
    }

    public SiteUser createUser(String username, String email, String password){
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        this.userRepository.save(user);
        return user;
    }
}
