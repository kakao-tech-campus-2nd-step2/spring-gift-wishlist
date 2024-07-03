package gift.controller;

import gift.service.ProductService;
import gift.domain.model.ProductDto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/products")
@Validated
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
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void getProduct(@RequestParam Long id, Model model) {
        productService.getProduct(id);
        populateModelWithProducts(model);
    }

    //    전체 상품 조회
    @GetMapping("/all")
    public String getAllProduct(Model model) {
        List<ProductDto> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "index";
    }

    //    상품 추가
    @PostMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductDto productDto, Model model) {
        productService.addProduct(productDto);
        populateModelWithProducts(model);
    }

    //    상품 수정
    @PutMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody ProductDto productDto, Model model) {
        productService.updateProduct(productDto);
        populateModelWithProducts(model);
    }

    //    상품 삭제
    @DeleteMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestParam Long id, Model model) {
        productService.deleteProduct(id);
        populateModelWithProducts(model);
    }
}
