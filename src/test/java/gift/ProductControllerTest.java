package gift;

import gift.Controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetProductById() throws Exception {
        // Create product
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":2,\"name\":\"coffee\",\"price\":4500, \"imageURL\":\"https://edu.nextstep.camp/s/dGx30MLc/ls/CRNATwfo\"}"))
                .andExpect(status().isCreated());

        // Retrieve product by ID
        mockMvc.perform(get("/api/products/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("coffee"))
                .andExpect(jsonPath("$.price").value(4500))
                .andExpect(jsonPath("$.imageURL").value("https://edu.nextstep.camp/s/dGx30MLc/ls/CRNATwfo"));
    }

    @Test
    public void testPostProduct() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"coffee\",\"price\":4500, \"imageURL\":\"https://edu.nextstep.camp/s/dGx30MLc/ls/CRNATwfo\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("coffee"))
                .andExpect(jsonPath("$.price").value(4500))
                .andExpect(jsonPath("$.imageURL").value("https://edu.nextstep.camp/s/dGx30MLc/ls/CRNATwfo"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        // Create product
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"coffee\",\"price\":4500, \"imageURL\":\"https://edu.nextstep.camp/s/dGx30MLc/ls/CRNATwfo\"}"))
                .andExpect(status().isCreated());

        // Update product
        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"coffee2\",\"price\":5500, \"imageURL\":\"https://edu.nextstep.camp/s/dGx30MLc/ls/CRNATwfo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("coffee2"))
                .andExpect(jsonPath("$.price").value(5500))
                .andExpect(jsonPath("$.imageURL").value("https://edu.nextstep.camp/s/dGx30MLc/ls/CRNATwfo"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        // Create product
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"coffee\",\"price\":4500, \"imageURL\":\"https://edu.nextstep.camp/s/dGx30MLc/ls/CRNATwfo\"}"))
                .andExpect(status().isCreated());

        // Delete product
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }
}

