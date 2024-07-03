package gift.controller;

import gift.model.Product;
import jakarta.validation.Valid;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import gift.service.ProductService;

import java.util.List;


@RequestMapping("/api/products")
@Controller
@Validated
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> addNewProduct(@Valid @RequestBody Product product) {
        String result = productService.addNewProduct(product);
        if ("Add successful".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleConstraintViolationException(MethodArgumentNotValidException ex) {
        FieldError firstError = (FieldError) ex.getBindingResult().getAllErrors().stream().findFirst().orElse(null);
        String errorMessage = "Unknown error";
        if (firstError != null) {
            errorMessage = firstError.getDefaultMessage();
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public String updateProduct(@RequestBody Product product) {
        String result = productService.updateProduct(product);
        return result;
    }

    @GetMapping("/edit/{id}")
    public String moveToEditProduct(@PathVariable Long id, Model model) {
        Product product = productService.selectProduct(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @GetMapping
    public String getProductList(Model model) {
        List<Product> products = productService.selectAllProducts();
        model.addAttribute("products", products);
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