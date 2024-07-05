package gift.service;

import gift.domain.User;
import gift.dto.Wishlist;
import gift.dto.Wishlist.Response;
import gift.repository.UserRepository;
import gift.repository.WishlistRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WishlistService {

    private final UserRepository userRepository;
    private final WishlistRepository wishlistRepository;

    public long findUserId(String accessToken) {
        return userRepository.findIdByAccessToken(accessToken);
    }

    public List<Wishlist.Response> getAllWishlistItems(long userId) {
        List<Response> items = wishlistRepository.findAllWishlistItems(userId);
        return items;
    }
}
