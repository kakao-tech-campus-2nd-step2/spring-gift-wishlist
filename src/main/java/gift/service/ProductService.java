package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.entity.ProductDao;
import gift.entity.ProductName;
import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import gift.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductResponseDto addProduct(ProductRequestDto productRequestDTO) {
        Product product = ProductMapper.toProduct(productRequestDTO);
        Product newProduct = new Product(null, product.name, product.price, product.imageUrl);
        Long productId = productDao.insertProduct(newProduct);
        Product createdProduct = new Product(productId, product.name, product.price, product.imageUrl);
        return ProductMapper.toProductResponseDTO(createdProduct);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDTO) {
        Product existingProduct = productDao.selectProduct(id)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "ID: " + id));

        Product updatedProduct = existingProduct.update(
                new ProductName(productRequestDTO.name),
                productRequestDTO.price,
                productRequestDTO.imageUrl);
        productDao.updateProduct(updatedProduct);
        return ProductMapper.toProductResponseDTO(updatedProduct);
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productDao.selectAllProducts();
        return products.stream()
                .map(ProductMapper::toProductResponseDTO)
                .collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id) {
        productDao.selectProduct(id)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "ID: " + id));
        productDao.deleteProduct(id);
        return true;
    }
}
