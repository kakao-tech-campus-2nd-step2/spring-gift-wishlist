//package gift.controller;
//
//import gift.model.Gift;
//import gift.service.GiftService;
//import org.junit.jupiter.api.*;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.*;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(AdminController.class)
//class AdminControllerTest {
//    @MockBean
//    private GiftService giftService;
//
//    @InjectMocks
//    private AdminController adminController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup(WebApplicationContext webApplicationContext) {
//        MockitoAnnotations.openMocks(this); // mock 객체를 초기화
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//    @Test
//    @DisplayName("메인 페이지 테스트")
//    public void testIndex() throws Exception {
//        mockMvc.perform(get("/"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/admin"));
//    }
//    @Test
//    @DisplayName("메인 페이지에 리스트가 잘 전달되는지 테스트")
//    public void testAdminHome() throws Exception {
//        Collection<Gift> giftList = Arrays.asList(
//                new Gift(1L, "Book", 20, "book.jpg"),
//                new Gift(2L, "Pen", 5, "pen.jpg")
//        );
//        when(giftService.getAllGifts()).thenReturn((List<Gift>) giftList);
//
//        mockMvc.perform(get("/admin"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("admin"))
//                .andExpect(model().attributeExists("giftlist"))
//                .andExpect(model().attribute("giftlist", giftList));
//    }
//    @Test
//    @DisplayName("상품 생성 테스트")
//    public void testGiftCreate() throws Exception {
//        mockMvc.perform(post("/admin/create")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("name", "Book")
//                        .param("price", "20")
//                        .param("imageUrl", "book.jpg"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/admin"));
//
//        //addGift를 정확히 한번 호출하였는가
//        verify(giftService, times(1)).addGift(any(Gift.class));
//    }
//
//    @Test
//    @DisplayName("상품 수정 테스트")
//    public void testGiftModify() throws Exception {
//        mockMvc.perform(put("/admin/modify/1")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("name", "Book")
//                        .param("price", "20")
//                        .param("imageUrl", "book.jpg"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/admin"));
//        //updateGift를 정확히 한번 호출하였는가 , id에 1L값이 정확하게 전달되었는가
//        verify(giftService, times(1)).updateGift(any(Gift.class), eq(1L));
//    }
//    @Test
//    @DisplayName("상품 삭제 테스트")
//    public void testGiftDelete() throws Exception {
//        mockMvc.perform(delete("/admin/delete/1"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/admin"));
//        // deleteGift를 정확히 한번 호출 하였는가
//        verify(giftService, times(1)).deleteGift(1L);
//    }
//}
