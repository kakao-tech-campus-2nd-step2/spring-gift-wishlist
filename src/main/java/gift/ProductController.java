package gift;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/products")
@Controller
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }
    @PostMapping
    public ResponseEntity<String> addNewProduct(@RequestBody Product product) {
        if (!productDao.checkProduct(product.id())) {
            productDao.insertProduct(product);
            return ResponseEntity.ok("Add successful");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Already exists id");
    }
    @PutMapping("/{id}")
    public String updateProduct(@RequestBody Product product) {
        productDao.updateProduct(product);
        return "Update Success";
    }
    @GetMapping("/edit/{id}")
    public String moveToEditProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productDao.selectProduct(id));
        return "editProduct";
    }
    @GetMapping
    public String getProductList(Model model) {
        model.addAttribute("products", productDao.selectAllProducts());
        return "productManage";
    }
    @GetMapping("/add")
    public String movtoAddProduct() {
        return "addProduct";
    }

    @DeleteMapping("/{id}")
    public String DeleteProduct(@PathVariable Long id){
        productDao.deleteProduct(id);
        return "productManage";
    }
    @PutMapping("/{id}/purchase")
    public ResponseEntity<String> purchaseProduct(@PathVariable Long id, @RequestParam int amount) {
        Product product = productDao.selectProduct(id);
        if (product.amount() >= amount) {
            productDao.purchaseProduct(id, amount);
            return ResponseEntity.ok("Purchase successful");
        }
        return ResponseEntity.badRequest().body("Not enough stock");
    }
}
