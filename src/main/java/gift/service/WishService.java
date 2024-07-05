package gift.service;

import gift.repository.ProductRepository;
import gift.repository.UserRepository;
import gift.repository.WishRepository;

public class WishService {

    private final WishRepository wishRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public WishService(WishRepository wishRepository, ProductRepository productRepository,
        UserRepository userRepository) {
        this.wishRepository = wishRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
}
