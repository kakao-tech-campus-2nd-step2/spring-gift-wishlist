package gift.repository;

import gift.Model.Item;
import gift.Model.ItemDTO;
import java.util.List;

public interface ItemRepository {
    void insert(ItemDTO itemDTO);
    Item findById(Long id);
    List<Item> findAll();
    void update(Long id, ItemDTO itemDTO);
    void delete(Long id);
}
