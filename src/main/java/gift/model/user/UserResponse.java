package gift.model.user;

import gift.model.product.Product;
import gift.model.product.ProductResponse;

public record UserResponse(String password, String email) {

    public static UserResponse from(User user) {
        return new UserResponse(user.getPassword(), user.getEmail());
    }
}
