package gift.service;

import gift.dto.ProductDto;
import gift.entity.Product;
import gift.dao.ProductDao;
import gift.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void save(ProductDto productDto) {
        Product product = new Product(
                productDto.getName(),
                productDto.getPrice(),
                productDto.getImgUrl()
        );
        productDao.save(product);
    }

    public List<ProductDto> findAll() {
        return productDao.findAll().stream()
                .map(product -> new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getImgUrl()))
                .collect(Collectors.toList());
    }

    public ProductDto findById(Long id) {
        Product product = productDao.findById(id);
        return new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getImgUrl());
    }

    public void update(Long id, ProductDto productDto) {
        Product product = productDao.findById(id);
        product.update(productDto.getName(), productDto.getPrice(), productDto.getImgUrl());
        productDao.update(product);
    }

    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    private void validateProductName(String productName) {
        if (productName != null && productName.contains("카카오")) {
            throw new GlobalExceptionHandler.KakaoProductException("상품명에 '카카오'가 포함된 경우 담당 MD에게 문의하세요.");
        }
    }
}