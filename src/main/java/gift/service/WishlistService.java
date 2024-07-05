package gift.service;

import gift.domain.Product;
import gift.repository.WishlistRepository;
import gift.response.ProductResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public List<ProductResponse> getProducts(Long memberId) {
        return wishlistRepository.findAllProducts(memberId).stream()
            .map(Product::toDto)
            .collect(Collectors.toList());
    }

    public void addProduct(Long memberId, Long productId) {
        wishlistRepository.saveProduct(memberId, productId);
    }

}
