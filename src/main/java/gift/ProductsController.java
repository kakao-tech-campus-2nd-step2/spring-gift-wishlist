package gift;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@Valid @RequestBody Product product) {

        if (product.getName().contains("카카오")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "'카카오'가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다.");
        }
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
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        boolean isSeller = authentication.getAuthorities()
            .contains(new SimpleGrantedAuthority(UserRole.SELLER.getValue()));
        if (isSeller) {
            Product targetProduct = productRepository.findById(id);
            // 추후 구현 예정
        }
        product.setId(id);
        return productRepository.update(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id, Authentication authentication) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        boolean isSeller = authentication.getAuthorities()
            .contains(new SimpleGrantedAuthority(UserRole.SELLER.getValue()));
        if (isSeller) {
            Product targetProduct = productRepository.findById(id);
            // 추후 구현 예정
        }

        productRepository.deleteById(id);
    }

    @GetMapping("/admin")
    public ModelAndView showAdminPage(@RequestParam(defaultValue = "1") int page) {
        List<Product> products = productRepository.findAll();
        int lastPage = (products.size() - 1) / 5 + 1;
        if (page < 1 || page > lastPage) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/index");
        modelAndView.addObject("products",
            products.subList(Math.max(0, page * 5 - 5), Math.min(page * 5, products.size())));
        modelAndView.addObject("productsCnt", products.size());
        modelAndView.addObject("page", page);
        modelAndView.addObject("startPage", Math.max(1, page - 2));
        modelAndView.addObject("endPage", Math.max(lastPage, page + 2));
        modelAndView.addObject("lastPage", lastPage);
        return modelAndView;
    }
}
