package gift.product.service;

import gift.product.dto.LoginMember;
import gift.product.dto.ProductDto;
import gift.product.model.Product;
import gift.product.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public static final String NAME_KAKAO = "카카오";
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductAll(LoginMember loginMember) {
        return productRepository.findAll(loginMember);
    }

    public Product getProduct(Long id, LoginMember loginMember) {
        return productRepository.findById(id, loginMember);
    }

    public Product insertProduct(ProductDto productDto, LoginMember loginMember) {
        Product product = new Product(productDto.name(), productDto.price(), productDto.imageUrl());
        product = productRepository.save(product, loginMember);

        return product;
    }

    public Product updateProduct(Long id, ProductDto productDTO, LoginMember loginMember) {
        Product product = new Product(id, productDTO.name(), productDTO.price(),
            productDTO.imageUrl());
        productRepository.update(product, loginMember);
        return product;
    }

    public void deleteProduct(Long id, LoginMember loginMember) {
        productRepository.delete(id, loginMember);
    }
}
