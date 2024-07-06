package gift.service;

import gift.Entity.LoginUser;
import gift.Entity.Wish;
import gift.repository.JdbcWishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    private final JdbcWishRepository repository;

    @Autowired
    public WishService(JdbcWishRepository repository) {
        this.repository = repository;
    }

    public void addWish(long productId, LoginUser loginUser) {
        repository.addToWishlist(loginUser.getEmail(), loginUser.getType(), productId);
    }

    public void removeWish(long productId, LoginUser loginUser) {
        repository.removeFromWishlist(loginUser.getEmail(), loginUser.getType(), productId);
    }

    public List<Wish> getWishesByMemberId(LoginUser loginUser) {
        return repository.getWishlistItems(loginUser.getEmail());
    }


}
