package gift.service;

import gift.dto.ProductRequestDTO;
import gift.dto.ProductResponseDTO;
import gift.entity.Product;
import gift.entity.ProductDao;
import gift.entity.ProductName;
import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import gift.mapper.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Product product = ProductMapper.toProduct(productRequestDTO);
        Product newProduct = new Product(null, product.name, product.price, product.imageUrl);
        Long productId = productDao.insertProduct(newProduct);
        Product createdProduct = new Product(productId, product.name, product.price, product.imageUrl);
        return ProductMapper.toProductResponseDTO(createdProduct);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product existingProduct = productDao.selectProduct(id)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "ID: " + id, HttpStatus.NOT_FOUND));

        Product updatedProduct = existingProduct.update(
                new ProductName(productRequestDTO.name),
                productRequestDTO.price,
                productRequestDTO.imageUrl);
        productDao.updateProduct(updatedProduct);
        return ProductMapper.toProductResponseDTO(updatedProduct);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productDao.selectAllProducts();
        return products.stream()
                .map(ProductMapper::toProductResponseDTO)
                .collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id) {
        productDao.selectProduct(id)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "ID: " + id, HttpStatus.NOT_FOUND));
        productDao.deleteProduct(id);
        return true;
    }
}
