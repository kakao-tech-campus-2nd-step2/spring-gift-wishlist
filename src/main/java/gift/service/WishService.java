package gift.service;

import gift.domain.Product;
import gift.domain.User;
import gift.domain.Wish;
import gift.dto.WishRequestDto;
import gift.dto.WishResponseDto;
import gift.exception.UserNotFoundException;
import gift.exception.WishNotFoundException;
import gift.repository.ProductRepository;
import gift.repository.UserRepository;
import gift.repository.WishRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private final WishRepository wishRepository;
    private final UserRepository userRepository;

    public WishService(WishRepository wishRepository, UserRepository userRepository) {
        this.wishRepository = wishRepository;
        this.userRepository = userRepository;
    }

    public void addWish(String userEmail, WishRequestDto wishRequestDto) {
        User user = userRepository.findByEmail(userEmail).get();
        wishRepository.addWish(new Wish(user.getId(),wishRequestDto.getProductId(),wishRequestDto.getQuantity()));
    }

}
