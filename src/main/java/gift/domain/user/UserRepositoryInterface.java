package gift.domain.user;

public interface UserRepositoryInterface {

    boolean existsByEmail(String email);

    void join(UserDTO userDTO);

    boolean checkUserInfo(UserDTO userDTO);

    User findByEmailAndPassword(UserDTO userDTO);
}
