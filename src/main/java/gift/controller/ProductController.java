package gift.controller;

import gift.dto.ProductDTO;
import gift.model.Product;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product.Builder().build());
        return "addProduct";
    }

    @PostMapping
    public String addProduct(@Valid @ModelAttribute("product") ProductDTO productDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addProduct";
        }
        try {
            Product product = new Product.Builder()
                    .name(productDto.getName())
                    .price(productDto.getPrice())
                    .imageUrl(productDto.getImageUrl())
                    .build();
            productService.addProduct(product);
            return "redirect:/products";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "addProduct";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }


    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, @Valid @ModelAttribute("product") ProductDTO productDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.toString()));
            return "editProduct";
        }
        try {
            Product updatedProduct = new Product.Builder()
                    .id(id)
                    .name(productDto.getName())
                    .price(productDto.getPrice())
                    .imageUrl(productDto.getImageUrl())
                    .build();
            productService.updateProduct(id, updatedProduct);
            return "redirect:/products";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "editProduct";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        try {
            productService.deleteProduct(id);
            return "redirect:/products";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
