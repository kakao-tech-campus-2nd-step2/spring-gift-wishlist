package gift.model.user;

import gift.model.product.Product;
import gift.model.product.ProductResponse;

public record UserResponse(Long id, String password, String email) {

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getPassword(), user.getEmail());
    }
}
