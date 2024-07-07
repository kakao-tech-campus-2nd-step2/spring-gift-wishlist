package gift.domain.cart;

import gift.domain.product.Product;
import gift.global.exception.BusinessException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final JdbcTemplate jdbcTemplate;
    private final CartItemRepository cartItemRepository;

    public CartItemService(JdbcTemplate jdbcTemplate, JdbcTemplateCartItemRepository jdbcTemplateCartRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.cartItemRepository = jdbcTemplateCartRepository;
    }

    /**
     * 장바구니에 상품 ID 추가
     */
    public void addCartItem(Long userId, Long productId) {
        if(cartItemRepository.isExistsInCart(userId, productId)){
            throw new BusinessException(HttpStatus.BAD_REQUEST, "해당 상품이 장바구니에 이미 존재합니다.");
        }

        cartItemRepository.addCartItem(userId, productId);
    }

    /**
     * 장바구니 조회
     */
    public List<Product> getCartItemsByUserId(Long userId) {
        List<Product> products = cartItemRepository.getCartItemsByUserId(userId);

        return products;
    }

    /**
     * 장바구니에서 상품 삭제
     */
    public void deleteCartItem(Long userId, Long productId) {
        cartItemRepository.deleteCartItem(userId, productId);
    }
}
