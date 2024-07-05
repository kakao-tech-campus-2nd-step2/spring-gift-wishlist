//package wishlist.controller;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import wishlist.model.item.Item;
//import wishlist.model.item.ItemDTO;
//import wishlist.model.item.ItemForm;
//import wishlist.service.ItemService;
//
//@WebMvcTest(ItemController.class)
//public class ItemControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ItemService itemService;
//
//    ItemForm form = new ItemForm("커피", 2000L, "url");
//    Long testId = 3L;
//
//    @Test
//    @DisplayName("리스트 요청 핸들러 테스트")
//    public void getListPageTest() throws Exception {
//        List<Item> list = new ArrayList<>();
//        given(itemService.getList()).willReturn(list);
//        mockMvc.perform(get("/product/list"))
//            .andExpect(status().isOk())
//            .andExpect(model().attributeExists("list"))
//            .andExpect(view().name("list"));
//    }
//
//    @Test
//    @DisplayName("상품 추가 요청 핸들러 테스트")
//    void createItemTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
//                .param("price", String.valueOf(form.getPrice()))
//                .param("name", form.getName())
//                .param("imgUrl", form.getImgUrl()))
//            .andExpect(status().is3xxRedirection())
//            .andExpect(redirectedUrl("/product/list"))
//            .andDo(print());
//    }
//
//    @Test
//    @DisplayName("상품 수정 페이지 요청 헨들러 테스트")
//    void updatePageTest() throws Exception {
//        ItemDTO item = new ItemDTO(testId, "김치", 2000L, "url");
//        given(itemService.findItem(testId)).willReturn(item);
//
//        mockMvc.perform(get("/product/update/{id}", testId))
//            .andExpect(status().isOk())
//            .andExpect(model().attribute("id", testId))
//            .andExpect(view().name("update"))
//            .andDo(print());
//    }
//}
