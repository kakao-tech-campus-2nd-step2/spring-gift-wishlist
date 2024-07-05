package gift.domain.user;

import gift.domain.user.dto.UserDTO;

public interface UserRepositoryInterface {

    boolean existsByEmail(String email);

    void join(UserDTO userDTO);

    boolean checkUserInfo(UserDTO userDTO);

    User findByEmailAndPassword(UserDTO userDTO);
}
