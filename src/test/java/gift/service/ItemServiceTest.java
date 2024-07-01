package gift.service;

import static org.assertj.core.api.Assertions.assertThat;

import gift.Model.Item;
import gift.Model.ItemDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    private final ItemDTO itemDTO = new ItemDTO("김치", 1200L, "URL");
    Long testId = 3L;

    @Test
    @DisplayName("상품 추가 기능 검사")
    void insertItemTest() {
        //when
        itemService.insertItem(itemDTO);
        Item item = itemService.findItem(testId);
        //then
        assertThat(item)
            .isNotNull()
            .extracting("name", "price", "imgUrl")
            .containsExactly(itemDTO.name(), itemDTO.price(), itemDTO.imgUrl());
    }

    @Test
    @Transactional
    @DisplayName("상품 수정 기능 검사")
    void updateItemTest(){
        //when
        itemService.insertItem(itemDTO);
        itemService.updateItem(new ItemDTO("커피", 2000L, "nnn"),testId);
        Item item = itemService.findItem(testId);
        //then
        assertThat(item)
            .isNotNull()
            .extracting("name","price","imgUrl")
            .containsExactly("커피",2000L,"nnn");
    }

    @Test
    @Transactional
    @DisplayName("상품 삭제 테스트")
    void deleteItemTest(){
        //when
        itemService.insertItem(itemDTO);
        itemService.deleteItem(testId);
        itemService.findItem(testId);
        //then
        assertThat(itemService.findItem(testId))
            .isNull();
    }
}
