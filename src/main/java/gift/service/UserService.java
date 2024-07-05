package gift.service;

import gift.jwt.JwtTokenProvider;
import gift.model.product.Product;
import gift.model.product.ProductResponse;
import gift.model.user.User;
import gift.repository.UserDao;
import gift.model.user.UserRequest;
import gift.model.user.UserResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserDao userDao, JwtTokenProvider jwtTokenProvider) {
        this.userDao = userDao;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public UserResponse register(UserRequest userRequest) {
        User user = userDao.save(userRequest);
        return UserResponse.from(user);
    }

    public String login(UserRequest userRequest) {
        User user = userDao.findByEmail(userRequest.email());
        return jwtTokenProvider.createToken(user.getEmail());
    }

    public User findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public List<ProductResponse> findWishList(Long userId) {
        List<Product> wishList = userDao.findWishList(userId);
        List<ProductResponse> responses = wishList.stream().map(ProductResponse::from)
            .collect(Collectors.toList());
        return responses;
    }

    public void addWistList(Long userId, Long productId, int count) {
        userDao.registerWishList(userId, productId, count);
    }

    public void deleteWishList(Long userId, Long productId, int count) {
        userDao.delete(userId, productId, count);
    }
}
