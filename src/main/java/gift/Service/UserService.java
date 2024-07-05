package gift.Service;

import gift.Model.Member;
import gift.Model.Product;
import gift.Repository.ProductRepository;
import gift.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean checkUserByMemberEmail(String email){
        return userRepository.findUserByEmail(email) != null;
    }

    public void addUser(Member member){
        userRepository.addUser(member);
    }
}
