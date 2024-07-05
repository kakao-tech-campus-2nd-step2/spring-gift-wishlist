package gift.repository;

import gift.Dto.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> save(User user);
    Boolean isExistEmail(String email);
    //User findByEmail(String username);
    //void delete(User user);
    //void update(User user);

}
