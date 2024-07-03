package gift;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping()
    public List<Product> view() {
        return productDao.getAllProducts();
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody ProductDto productDto) {
        productDao.insert(productDto);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody ProductDto productDto) {
        productDao.update(id, productDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        productDao.delete(id);
    }
}
