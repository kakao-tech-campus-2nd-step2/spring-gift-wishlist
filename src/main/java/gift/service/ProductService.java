package gift.service;

import gift.model.Product;
import gift.controller.ProductDTO;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findProductsAll();
    }

    public Product findProductsById(Long id) {
        return productRepository.findProductsById(id);
    }

    public void saveProduct(ProductDTO productDTO) {
        validateProduct(productDTO);
        productRepository.saveProduct(toEntity(productDTO, null));
    }

    public void updateProduct(ProductDTO productDTO, Long id) {
        validateProduct(productDTO);
        productRepository.updateProduct(toEntity(productDTO, id), id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(product.name(), String.valueOf(product.price()), product.imageUrl());
    }

    public static Product toEntity(ProductDTO productDTO, Long id) {
        return new Product(id, productDTO.name(), productDTO.price(), productDTO.imageUrl());
    }

    private void validateProduct(ProductDTO productDTO) {
        if (productDTO.name() == null || productDTO.name().isBlank()) {
            throw new IllegalArgumentException("상품 이름은 최소 1자 이상이어야 합니다.");
        }
        if (productDTO.name().length() > 15) {
            throw new IllegalArgumentException("상품 이름은 공백 포함 최대 15자까지 입력할 수 있습니다.");
        }
        if (!productDTO.name().matches("^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\s\\(\\)\\[\\]\\+\\-\\&\\/\\_]*$")) {
            throw new IllegalArgumentException("상품 이름에 (), [], +, -, &, /, _ 외 특수 문자는 사용할 수 없습니다.");
        }
        if (productDTO.name().contains("카카오")) {
            throw new IllegalArgumentException("'카카오'가 포함된 문구는 담당 MD와 협의 후 사용 바랍니다.");
        }
        if (productDTO.price() == null || productDTO.price().isBlank()) {
            throw new IllegalArgumentException("가격을 입력해야 합니다.");
        }
        if (productDTO.price() == null || !productDTO.price().matches("^\\d+$")) {
            throw new IllegalArgumentException("가격은 0이상의 숫자만 입력 가능합니다.");
        }
        if (productDTO.imageUrl() == null || productDTO.imageUrl().isBlank()) {
            throw new IllegalArgumentException("이미지 URL을 입력해야 합니다.");
        }
        if (productDTO.imageUrl() == null || !productDTO.imageUrl()
            .matches("^(http|https)://.*$")) {
            throw new IllegalArgumentException("유효한 이미지 URL을 입력해야 합니다.");
        }
    }
}
