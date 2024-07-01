package gift;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    private final ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product) {
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
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        product.setId(id);
        return productRepository.update(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

    @GetMapping("/admin")
    public ModelAndView showAdminPage(@RequestParam(defaultValue = "1") int page) {
        List<Product> products = productRepository.findAll();
        int lastPage = (products.size()-1) / 5 + 1;
        if (page < 1 || page > lastPage) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/index");
        modelAndView.addObject("products",
            products.subList(Math.max(0, page * 5 - 5), Math.min(page * 5, products.size())));
        modelAndView.addObject("productsCnt", products.size());
        modelAndView.addObject("page", page);
        modelAndView.addObject("startPage", Math.max(1, page-2));
        modelAndView.addObject("endPage", Math.max(lastPage, page+2));
        modelAndView.addObject("lastPage", lastPage);
        return modelAndView;
    }

}
