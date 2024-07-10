package gift.service;

import gift.dto.UserInfoDTO;
import gift.dto.WishResponseEntity;
import gift.repository.UserDAO;
import gift.repository.WishDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private final WishDAO wishDAO;
    private final UserDAO userDAO;

    public WishService(WishDAO wishDAO, UserDAO userDAO) {
        this.wishDAO = wishDAO;
        this.userDAO = userDAO;
    }

    public List<WishResponseEntity> getWishes(String email) {
        long userId = userDAO.findUserByEmail(email).id();

        return wishDAO.findWishes(userId).stream().map((wish) -> new WishResponseEntity(
                wish.id(),
                wish.productId(),
                wish.quantity()
        )).toList();
    }
}
