package gift.service;

import gift.dto.WishListItemRequest;
import gift.dto.WishListItemResponse;
import gift.model.Member;
import gift.model.Product;
import gift.model.WishList;
import gift.model.WishListItem;
import gift.repository.ProductRepository;
import gift.repository.WishListRepository;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;

    public WishListService(WishListRepository wishListRepository,
        ProductRepository productRepository) {
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
    }

    public WishListItemResponse addItemToWishList(WishListItemRequest request, Member member) {
        WishList wishList = wishListRepository.getOrCreateWishList(member.getId());
        Product product = productRepository.findById(request.getProductId());
        if (product == null) {
            throw new NoSuchElementException("Product not found");
        }

        wishListRepository.addItemToWishList(wishList.getId(), product.getId(), 1);

        return new WishListItemResponse(wishList.getId(), product.getId(), product.getName(),
            product.getPrice(), product.getImageUrl(),1);
    }

    public List<WishListItemResponse> getWishListItems(Member member) {
        WishList wishList = wishListRepository.getOrCreateWishList(member.getId());
        List<WishListItem> items = wishListRepository.getWishListItems(wishList.getId());

        return items.stream()
            .map(item -> {
                Product product = productRepository.findById(item.getProductId());
                if (product == null) {
                    throw new NoSuchElementException("Product not found");
                }
                return new WishListItemResponse(item.getId(), product.getId(), product.getName(),
                    product.getPrice(), product.getImageUrl(),item.getQuantity());
            })
            .collect(Collectors.toList());
    }

    public void removeItemFromWishList(Long itemId,Member member) {
        var wishList = wishListRepository.getOrCreateWishList(member.getId());
        wishListRepository.removeItemFromWishList(wishList.getId(), itemId);
    }

    public void changeItemNumberFromWishList(Long productId, int quantity, Member member) {
        var wishList = wishListRepository.getOrCreateWishList(member.getId());
        wishListRepository.changeItemNumberFromWishList(wishList.getId(),productId,quantity);
    }
}