package gift.product.controller;

import gift.product.dto.ProductDto;
import gift.product.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// main-page view를 반환하는 컨트롤러
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String ADMIN_PAGE = "html/admin";

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 제품을 추가하고 view를 반환하는 핸들러
    // 수정: ModelAttribute라는 좋은 어노테이션이 있었다.
    @PostMapping("/requests")
    public String createProduct(@ModelAttribute @Valid ProductDto productDto, Model model) {
        // service로 부터 적절한 반환값을 가져 옴.
        List<ProductDto> products = productService.insertProduct(productDto);

        addAttributesForManagerPage(products, model);
        return ADMIN_PAGE;
    }

    // 전체 상품을 담은 view를 반환하는 핸들러
    @GetMapping("/main-page")
    public String readProducts(Model model) {
        List<ProductDto> products = productService.selectProducts();

        addAttributesForManagerPage(products, model);
        return ADMIN_PAGE;
    }

    // id가 i인 상품을 수정하는 핸들러
    @PostMapping("/{id}/changes")
    public String updateProduct(@PathVariable(name = "id") long targetId,
        @ModelAttribute ProductDto productDto, Model model) {
        // service를 호출해서 제품 수정
        List<ProductDto> products = productService.updateProduct(productDto);

        addAttributesForManagerPage(products, model);
        return ADMIN_PAGE;
    }

    // id가 i인 상품을 삭제하는 핸들러
    @PostMapping("/{id}/removal")
    public String deleteProduct(@PathVariable(name = "id") long id, Model model) {
        // service를 사용해서 하나의 제품 제거
        List<ProductDto> products = productService.deleteProduct(id);

        addAttributesForManagerPage(products, model);
        return ADMIN_PAGE;
    }

    // 모든 상품을 삭제하는 핸들러
    @PostMapping("/removal")
    public String deleteProducts(Model model) {
        // 모든 제품 제거
        List<ProductDto> products = productService.deleteProducts();

        addAttributesForManagerPage(products, model);
        return ADMIN_PAGE;
    }

    // manager.html에서 보여줄 attributes를 넣는 함수
    private void addAttributesForManagerPage(List<ProductDto> productsList, Model model) {
        // 제품 목록 넣어줌
        model.addAttribute("products", productsList);
    }
}
