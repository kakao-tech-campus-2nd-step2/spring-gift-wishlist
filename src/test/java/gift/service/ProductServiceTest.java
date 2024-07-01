package gift.service;

import gift.entity.Product;
import gift.entity.ProductDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void 상품_추가() {
        Map<String, Object> productData = new HashMap<>();
        productData.put("name", "아이스 카페 아메리카노 T");
        productData.put("price", 4500);
        productData.put("imageUrl", "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

        Product product = new Product(null, "아이스 카페 아메리카노 T", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        when(productDao.insertProduct(any(Product.class))).thenReturn(1L);

        Product addedProduct = productService.addProduct(productData);

        assertNotNull(addedProduct);
        assertEquals(1L, addedProduct.id);
        assertEquals("아이스 카페 아메리카노 T", addedProduct.name);
        assertEquals(4500, addedProduct.price);
        assertEquals("https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg", addedProduct.imageUrl);
    }

    @Test
    public void 상품_조회() {
        Product product = new Product(1L, "오둥이 입니다만", 29800, "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg");
        when(productDao.selectAllProducts()).thenReturn(List.of(product));

        List<Product> productList = productService.getAllProducts();

        assertNotNull(productList);
        assertEquals(1, productList.size());
        Product retrievedProduct = productList.get(0);
        assertEquals("오둥이 입니다만", retrievedProduct.name);
        assertEquals(29800, retrievedProduct.price);
        assertEquals("https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg", retrievedProduct.imageUrl);
    }

    @Test
    public void 상품_수정() {
        Product originalProduct = new Product(1L, "오둥이 입니다만", 29800, "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg");
        when(productDao.selectProduct(1L)).thenReturn(originalProduct);

        Map<String, Object> updatedProductData = new HashMap<>();
        updatedProductData.put("name", "오둥이 아닙니다만");
        updatedProductData.put("price", 35000);
        updatedProductData.put("imageUrl", "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg");

        Product updatedProduct = new Product(1L, "오둥이 아닙니다만", 35000, "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg");
        doNothing().when(productDao).updateProduct(any(Product.class));

        Product result = productService.updateProduct(1L, updatedProductData);

        assertNotNull(result);
        assertEquals(1L, result.id);
        assertEquals("오둥이 아닙니다만", result.name);
        assertEquals(35000, result.price);
        assertEquals("https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg", result.imageUrl);
    }

    @Test
    public void 상품_수정_없는상품() {
        Map<String, Object> updatedProductData = new HashMap<>();
        updatedProductData.put("name", "오둥이 아닙니다만");
        updatedProductData.put("price", 35000);
        updatedProductData.put("imageUrl", "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg");

        when(productDao.selectProduct(100L)).thenReturn(null);

        Product updatedProduct = productService.updateProduct(100L, updatedProductData);

        assertNull(updatedProduct);
    }

    @Test
    public void 상품_삭제() {
        Product product = new Product(1L, "오둥이 입니다만", 29800, "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg");
        when(productDao.selectProduct(1L)).thenReturn(product);
        doNothing().when(productDao).deleteProduct(1L);
        when(productDao.selectAllProducts()).thenReturn(List.of());

        boolean result = productService.deleteProduct(1L);
        assertTrue(result);

        List<Product> productList = productService.getAllProducts();
        assertTrue(productList.isEmpty());
    }

    @Test
    public void 상품_삭제_없는상품() {
        when(productDao.selectProduct(2L)).thenReturn(null);

        boolean result = productService.deleteProduct(2L);
        assertFalse(result);
    }
}
