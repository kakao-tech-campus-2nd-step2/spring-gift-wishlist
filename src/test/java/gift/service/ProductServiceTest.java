package gift.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import gift.controller.ProductRequest;
import gift.domain.Product;
import gift.domain.ProductRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @DisplayName("모든 상품 정보를 조회해 반환한다.")
    @Test
    void getProducts() throws Exception {
        //given
        given(productRepository.findAll()).willReturn(List.of());

        //when
        productService.getProducts();

        //then
        then(productRepository).should().findAll();
    }

    @DisplayName("아이디를 받아 해당하는 상품 정보를 조회해 반환한다.")
    @Test
    void getProduct() throws Exception {
        //given
        Long productId = 1L;
        Product product = new Product();

        given(productRepository.findById(productId)).willReturn(product);

        //when
        productService.getProduct(productId);

        //then
        then(productRepository).should().findById(productId);
    }

    @DisplayName("상품 하나를 추가한다.")
    @Test
    void addProduct() throws Exception {
        //given
        ProductRequest request = new ProductRequest();

        willDoNothing().given(productRepository).save(any(Product.class));

        //when
        productService.addProduct(request);

        //then
        then(productRepository).should().save(any(Product.class));
    }

    @DisplayName("상품 정보를 수정한다.")
    @Test
    void editProduct() throws Exception {
        //given
        Long productId = 1L;
        ProductRequest request = new ProductRequest();
        Product product = new Product();

        willDoNothing().given(productRepository).edit(productId, product);

        //when
        productService.editProduct(productId, request);

        //then
        then(productRepository).should().edit(productId, product);
    }

    @DisplayName("상품 하나를 삭제한다.")
    @Test
    void removeProduct() throws Exception {
        //given
        Long productId = 1L;

        willDoNothing().given(productRepository).deleteById(productId);

        //when
        productService.removeProduct(productId);

        //then
        then(productRepository).should().deleteById(productId);
    }

}
