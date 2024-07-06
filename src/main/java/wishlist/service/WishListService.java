package wishlist.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import wishlist.exception.CustomException.ItemNotFoundException;
import wishlist.exception.ErrorCode;
import wishlist.model.item.Item;
import wishlist.model.item.ItemDTO;
import wishlist.model.wishList.WishItem;
import wishlist.repository.ItemRepository;
import wishlist.repository.WishListRepository;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final ItemRepository itemRepository;

    public WishListService(WishListRepository wishListRepository, ItemRepository itemRepository) {
        this.wishListRepository = wishListRepository;
        this.itemRepository = itemRepository;
    }

    public List<ItemDTO> getList(Long UserId) {
        List<Item> list = wishListRepository.findAllByUserId(UserId);
        return list.stream()
            .map(item -> new ItemDTO(item.getId(), item.getName(), item.getPrice(),
                item.getImgUrl()))
            .collect(Collectors.toList());
    }

    public void addToWishList(Long userId, Long itemId) {
        Item item = itemRepository.findById(itemId);
        WishItem wishItem = new WishItem(0L, userId, item.getId());
        wishListRepository.insert(wishItem);
    }

    public void deleteFromWishList(Long userId, Long itemId) {
        try {
            Item item = itemRepository.findById(itemId);
            wishListRepository.delete(userId, itemId);
        } catch (Exception e) {
            throw new ItemNotFoundException(ErrorCode.ITEM_NOT_FOUND);
        }
    }
}