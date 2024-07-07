package gift.web;

import gift.web.dto.ProductDto;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    private final AtomicLong incrementCounter = new AtomicLong(1); // ID를 관리할 변수

    public List<ProductDto> getProducts() {
        return List.copyOf(productDAO.selectAllProducts());
    }

    public ProductDto getProductById(Long id) {
        return productDAO.selectProductById(id);
    }

    public ProductDto createProduct(ProductDto productDto) {
        ProductDto newProductDto = productDAO.insertProduct(productDto);
        return newProductDto;
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        productDAO.selectProductById(id);
        ProductDto newProductDto = new ProductDto(id, productDto.name(), productDto.price(), productDto.imageUrl());
        productDAO.updateProduct(newProductDto);
        return newProductDto;
    }

    public void deleteProduct(Long id) {
        productDAO.selectProductById(id);
        productDAO.deleteProductById(id);
    }
}
