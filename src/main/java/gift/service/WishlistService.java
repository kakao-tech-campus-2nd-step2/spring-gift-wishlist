package gift.service;

import gift.domain.Product;
import gift.domain.member.Member;
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

    public List<ProductResponse> getProducts(Member member) {
        return wishlistRepository.findAllProducts(member.getId()).stream()
            .map(Product::toDto)
            .collect(Collectors.toList());
    }

}
