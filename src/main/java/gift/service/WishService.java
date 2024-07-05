package gift.service;

import gift.domain.Product;
import gift.domain.User;
import gift.domain.Wish;
import gift.dto.WishRequestDto;
import gift.exception.ProductNotFoundException;
import gift.exception.UserNotFoundException;
import gift.repository.ProductRepository;
import gift.repository.UserRepository;
import gift.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishService {
    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WishService(WishRepository wishRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.wishRepository = wishRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void save(String userEmail, WishRequestDto request){
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("해당 email의 유저가 존재하지 않습니다."));
        Product product = productRepository.findByName(request.getProductName())
                .orElseThrow(() -> new ProductNotFoundException("해당 이름의 상품이 존재하지 않습니다."));;
        wishRepository.save(new Wish(user.getId(),product.getId(), request.getQuantity()));
    }
}
