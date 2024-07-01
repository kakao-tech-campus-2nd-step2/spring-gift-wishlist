package gift.repository;

import gift.model.Item;
import gift.model.ItemDTO;
import java.util.List;

public interface ItemRepository {
    void insert(ItemDTO itemDTO);
    Item findById(Long id);
    List<Item> findAll();
    void update(Long id, ItemDTO itemDTO);
    void delete(Long id);
}
