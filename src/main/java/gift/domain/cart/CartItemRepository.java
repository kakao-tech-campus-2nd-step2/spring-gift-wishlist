package gift.domain.cart;

import gift.domain.product.Product;
import java.util.List;

public interface CartItemRepository {

    boolean isExistsInCart(Long userId, Long productId);

    void addCartItem(Long userId, Long productId);

    List<Product> getCartItemsByUserId(Long userId);

    void deleteCartItem(Long userId, Long productId);
}
