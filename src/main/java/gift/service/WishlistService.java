package gift.service;

import gift.dto.Wishlist;
import gift.dto.Wishlist.Request;
import gift.dto.Wishlist.Response;
import gift.repository.ProductJdbcRepository;
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
    private final ProductJdbcRepository productRepository;

    public long findUserId(String accessToken) {
        return userRepository.findIdByAccessToken(accessToken);
    }

    public List<Wishlist.Response> getAllWishlistItems(long userId) {
        List<Response> items = wishlistRepository.findAllWishlistItems(userId);
        return items;
    }

    public Response addItemToWishlist(long userId, Request request) {
        long productId = productRepository.findIdByName(request.getProductName());

        wishlistRepository.save(userId, productId, request);
        return Response.builder()
            .productName(request.getProductName())
            .quantity(request.getQuantity())
            .build();
    }

    public Response deleteItemFromWishlist(long userId, Request request) {
        long productId = productRepository.findIdByName(request.getProductName());

        wishlistRepository.deleteByProductId(userId, productId);
        return Response.builder()
            .productName(request.getProductName())
            .quantity(request.getQuantity())
            .build();
    }
}
