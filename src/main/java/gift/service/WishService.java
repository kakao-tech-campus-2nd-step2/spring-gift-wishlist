package gift.service;

import gift.dto.WishRequestDto;
import gift.dto.WishResponseDto;
import gift.entity.Wish;
import gift.entity.WishDao;
import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import gift.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishService {
    private final WishDao wishDao;
    private final ProductService productService;

    public WishService(WishDao wishDao, ProductService productService) {
        this.wishDao = wishDao;
        this.productService = productService;
    }

    public WishResponseDto addWish(Long userId, WishRequestDto wishRequestDto) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        Wish wish = new Wish(null, userId, wishRequestDto.productId);
        Wish createdWish = wishDao.insertWish(wish);

        return toWishResponseDto(createdWish);
    }

    public List<WishResponseDto> getWishesByUserId(Long userId) {
        List<Wish> wishes = wishDao.selectWishesByUserId(userId);
        return wishes.stream()
                .map(this::toWishResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteWish(Long wishId) {
        wishDao.selectWish(wishId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "ID: " + wishId));
        wishDao.deleteWish(wishId);
    }

    private WishResponseDto toWishResponseDto(Wish wish) {
        var productResponseDto = productService.getAllProducts().stream()
                .filter(p -> p.id.equals(wish.productId))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "ID: " + wish.productId));
        return new WishResponseDto(wish.id, productResponseDto.id, productResponseDto.name, productResponseDto.price, productResponseDto.imageUrl);
    }
}
