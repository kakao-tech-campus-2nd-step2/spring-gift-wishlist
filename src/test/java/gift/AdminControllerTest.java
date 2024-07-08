package gift;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gift.prodcut.AdminController;
import gift.prodcut.Product;
import gift.prodcut.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

public class AdminControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void testGetProduct() throws Exception {
        // Given
        List<Product> products = new ArrayList<>();
        when(productService.getAllProducts()).thenReturn(products);

        // When & Then
        mockMvc.perform(get("/admin/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attribute("products", products));
    }

    @Test
    public void testAddProduct() throws Exception {
        mockMvc.perform(post("/admin/products/add")
                        .param("id", "1")
                        .param("productName", "Test Product")
                        .param("description", "Test Description")
                        .param("price", "10000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/products"));

        verify(productService, times(1)).addProduct(any(Product.class));
    }
}