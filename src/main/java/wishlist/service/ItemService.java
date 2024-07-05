package wishlist.service;

import java.util.stream.Collectors;
import wishlist.model.Item;
import wishlist.model.ItemDTO;
import wishlist.model.ItemForm;
import wishlist.repository.ItemRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void insertItem(ItemForm form) {
        Item item = new Item(form.getName(), form.getPrice(), form.getImgUrl());
        itemRepository.insert(item);
    }

    public ItemDTO findItem(Long id) {
        Item item = itemRepository.findById(id);
        return new ItemDTO(item.getId(), item.getName(), item.getPrice(), item.getImgUrl());
    }

    public List<ItemDTO> getList() {
        return itemRepository.findAll().stream()
            .map(item -> new ItemDTO(item.getId(), item.getName(), item.getPrice(),
                item.getImgUrl()))
            .collect(Collectors.toList());
    }

    public void updateItem(ItemDTO itemDTO) {
        Item item = new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getPrice(),
            itemDTO.getImgUrl());
        itemRepository.update(item);
    }

    public void deleteItem(Long id) {
        itemRepository.delete(id);
    }
}
