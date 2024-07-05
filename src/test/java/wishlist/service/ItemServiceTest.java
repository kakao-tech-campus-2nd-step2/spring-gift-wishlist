//package wishlist.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import wishlist.model.item.ItemDTO;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import wishlist.model.item.ItemForm;
//
//@SpringBootTest
//public class ItemServiceTest {
//
//    @Autowired
//    private ItemService itemService;
//
//    private final ItemDTO itemDTO = new ItemDTO(3L, "김치", 1200L, "URL");
//    Long testId = 3L;
//
//    @Test
//    @DisplayName("상품 추가 기능 검사")
//    void insertItemTest() {
//        //when
//        itemService.insertItem(new ItemForm("김치", 1200L, "URL"));
//        ItemDTO item = itemService.findItem(testId);
//        //then
//        assertThat(item)
//            .isNotNull()
//            .extracting("name", "price", "imgUrl")
//            .containsExactly(itemDTO.gname(), itemDTO.price(), itemDTO.imgUrl());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("상품 수정 기능 검사")
//    void updateItemTest() {
//        //when
//        itemService.insertItem(new ItemForm(itemDTO.name(), itemDTO.price(), itemDTO.imgUrl()));
//        itemService.updateItem(new ItemDTO(testId, "커피", 2000L, "nnn"));
//        ItemDTO item = itemService.findItem(testId);
//        //then
//        assertThat(item)
//            .isNotNull()
//            .extracting("name", "price", "imgUrl")
//            .containsExactly("커피", 2000L, "nnn");
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("상품 삭제 테스트")
//    void deleteItemTest() {
//        //when
//        itemService.insertItem(new ItemForm("김치", 1200L, "URL"));
//        itemService.deleteItem(testId);
//        ItemDTO find = itemService.findItem(testId);
//        //then
//        assertThat(find)
//            .isNull();
//    }
//}