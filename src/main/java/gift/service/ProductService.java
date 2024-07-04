package gift.service;

import gift.dto.ProductRequestDTO;
import gift.model.Product;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(ProductRequestDTO productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setImageUrl(productRequest.getImageUrl());
        product.setPrice(productRequest.getPrice());
        productRepository.save(product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, ProductRequestDTO productRequest) {
        Optional<Product> existingProductOpt = productRepository.findById(id);
        if (existingProductOpt.isEmpty()) {
            return Optional.empty();
        }
        Product existingProduct = existingProductOpt.get();
        existingProduct.setName(productRequest.getName());
        existingProduct.setImageUrl(productRequest.getImageUrl());
        existingProduct.setPrice(productRequest.getPrice());
        productRepository.save(existingProduct);
        return Optional.of(existingProduct);
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
