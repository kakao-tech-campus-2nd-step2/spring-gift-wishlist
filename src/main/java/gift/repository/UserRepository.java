package gift.repository;

import gift.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByEmail(String email);
}
