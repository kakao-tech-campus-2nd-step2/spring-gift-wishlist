package gift.doamin.product.controller;

import gift.doamin.product.entity.Product;
import gift.doamin.product.repository.ProductRepository;
import gift.doamin.user.entity.UserRole;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@Valid @RequestBody Product product, Principal principal) {
        Long userId = Long.valueOf(principal.getName());

        if (product.getName().contains("카카오")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "'카카오'가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다.");
        }

        product.setUserId(userId);
        return productRepository.insert(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product,
        Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());

        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Product targetProduct = productRepository.findById(id);

        boolean isSeller = authentication.getAuthorities()
            .contains(new SimpleGrantedAuthority(UserRole.SELLER.getValue()));
        if (isSeller) {
            if (!targetProduct.getUserId().equals(userId)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }
        product.setId(id);
        product.setUserId(targetProduct.getUserId());
        return productRepository.update(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id, Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());

        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Product targetProduct = productRepository.findById(id);

        boolean isSeller = authentication.getAuthorities()
            .contains(new SimpleGrantedAuthority(UserRole.SELLER.getValue()));
        if (isSeller) {
            if (!targetProduct.getUserId().equals(userId)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }

        productRepository.deleteById(id);
    }
}
