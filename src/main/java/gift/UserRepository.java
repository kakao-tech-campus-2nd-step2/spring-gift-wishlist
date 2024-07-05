package gift;

public interface UserRepository {
    void save(User user);
    User findById(Long id);
    User findByEmail(String email);
}
