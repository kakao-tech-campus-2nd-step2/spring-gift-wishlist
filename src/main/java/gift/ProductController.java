package gift;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import gift.service.ProductService;


@RequestMapping("/api/products")
@Controller
@Validated
public class ProductController {
    private final ProductDao productDao;
    private final ProductService productService;


    @Autowired
    private MessageSource messageSource;

    public ProductController(ProductDao productDao, ProductService productService) {
        this.productService = productService;
        this.productDao = productDao;
    }

    @PostMapping
    public ResponseEntity<String> addNewProduct(@Valid @RequestBody Product product) {
        if (productDao.checkProduct(product.id())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Already exists id");
        }
        productDao.insertProduct(product);
        return ResponseEntity.ok("Add successful");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleConstraintViolationException(MethodArgumentNotValidException ex) {
        FieldError firstError = (FieldError) ex.getBindingResult().getAllErrors().stream().findFirst().orElse(null);
        String errorMessage = "Unknown error";
        if (firstError != null) {
            String fieldName = firstError.getField();
            errorMessage = firstError.getDefaultMessage();
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
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
        String result = productService.purchaseProduct(id, amount);
        if ("Purchase successful".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}