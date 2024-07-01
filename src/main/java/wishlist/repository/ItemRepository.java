package wishlist.repository;

import wishlist.model.Item;
import wishlist.model.ItemDTO;
import java.util.List;

public interface ItemRepository {
    Long insert(ItemDTO itemDTO);
    Item findById(Long id);
    List<Item> findAll();
    void update(Long id, ItemDTO itemDTO);
    void delete(Long id);
}
