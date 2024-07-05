package gift.controller;

import gift.domain.Wish;
import gift.service.WishService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.*;

@WebMvcTest(WishController.class)
public class WishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishService wishService;

    @Test
    public void testAddWish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wishes")
                        .contentType("application/json")
                        .content("{\"memberId\":1,\"productId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        ArgumentCaptor<Wish> wishArgumentCaptor = ArgumentCaptor.forClass(Wish.class);
        verify(wishService, times(1)).addWish(1L, 1L);
    }

    @Test
    public void testShowForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wishes/form"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("wishlistForm"));
    }

    @Test
    public void testGetWishes() throws Exception {
        Long memberId = 1L;
        Wish wish = new Wish(memberId, 1L);
        when(wishService.getWishes(memberId)).thenReturn(Collections.singletonList(wish));

        mockMvc.perform(MockMvcRequestBuilders.get("/wishes").param("memberId", memberId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("myWishlist"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("wishes"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("memberId"));
    }

    @Test
    public void testRemoveWish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/wishes/remove")
                        .contentType("application/json")
                        .content("{\"memberId\":1,\"productId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(wishService, times(1)).removeWish(1L, 1L);
    }
}
