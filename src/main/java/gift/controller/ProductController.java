package gift.controller;

import gift.service.ProductService;
import gift.domain.model.ProductDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private String populateModelWithProducts(Model model) {
        List<ProductDto> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping()
    public String getProduct(@RequestParam Long id, Model model) {
        try {
            productService.getProduct(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return populateModelWithProducts(model);
    }


    @GetMapping("/all")
    public String getAllProduct(Model model) {
        try {
            List<ProductDto> products = productService.getAllProduct();
            model.addAttribute("products", products);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "index";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto, Model model) {
        try {
            productService.addProduct(productDto);
            return ResponseEntity.ok(populateModelWithProducts(model));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDto productDto, Model model) {
        try {
            productService.updateProduct(productDto);
            return ResponseEntity.ok(populateModelWithProducts(model));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam Long id, Model model) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(populateModelWithProducts(model));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
