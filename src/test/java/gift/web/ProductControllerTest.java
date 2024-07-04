package gift.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.web.dto.Product;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductController productController;

    private Map<Long, Product> products;

    @BeforeEach
    void setup() {
        products = new HashMap<>();

        products.put(1L, new Product(1L, "Test Product 1", 100L, "http://example.com/1.jpg"));
        products.put(2L, new Product(2L, "Test Product 2", 200L, "http://example.com/2.jpg"));

        ReflectionTestUtils.setField(productController, "products", products);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testGetProducts() throws Exception {
        mockMvc.perform(get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void testGetProduct() throws Exception {
        products.put(1L, new Product(1L, "Test Product 1", 100L, "http://example.com/1.jpg"));

        mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Test Product 1"));
    }

    @Test
    void testGetProductNotFound() throws Exception {
        mockMvc.perform(get("/api/products/999")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void testCreateProduct() throws Exception {
        Product newProduct = new Product(3L, "New Product", 300L, "http://example.com/3.jpg");

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(3))
            .andExpect(jsonPath("$.name").value("New Product"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        products.put(1L, new Product(1L, "Test Product 1", 100L, "http://example.com/1.jpg"));

        Product updatedProduct = new Product(1L, "Updated Product", 150L, "http://example.com/1.jpg");

        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Updated Product"));
    }

    @Test
    void testCreateProductWithPut() throws Exception {
        Product newProduct = new Product(4L, "New Product with PUT", 400L, "http://example.com/4.jpg");

        mockMvc.perform(put("/api/products/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(4))
            .andExpect(jsonPath("$.name").value("New Product with PUT"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Delete Success"));
    }

    @Test
    void testDeleteProductNotFound() throws Exception {
        mockMvc.perform(delete("/api/products/999")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Product Not Found"));
    }
}
