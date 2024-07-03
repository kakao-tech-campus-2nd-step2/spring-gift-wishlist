package gift.controller;

import gift.service.ProductService;
import gift.domain.model.ProductDto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void populateModelWithProducts(Model model) {
        List<ProductDto> products = productService.getAllProduct();
        model.addAttribute("products", products);
    }

//    id로 상품 조회
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public void getProduct(@RequestParam Long id, Model model) {
        try {
            productService.getProduct(id);
            populateModelWithProducts(model);
        } catch (Exception e) {
            e.getMessage();
        }
    }

//    전체 상품 조회
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

//    상품 추가
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductDto productDto, Model model) {
        try {
            productService.addProduct(productDto);
            populateModelWithProducts(model);
        } catch (Exception e) {
            e.getMessage();
        }
    }

//    상품 수정
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody ProductDto productDto, Model model) {
        try {
            productService.updateProduct(productDto);
            populateModelWithProducts(model);
        } catch (Exception e) {
            e.getMessage();
        }
    }

//    상품 삭제
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestParam Long id, Model model) {
        try {
            productService.deleteProduct(id);
            populateModelWithProducts(model);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
