package gift.product.controller;

import gift.product.dto.ProductDto;
import gift.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// edit-page view를 반환하는 컨트롤러
// 컨트롤러가 반환하는 html 기준으로 나눴습니다.
@Controller
public class EditPageController {
    private final ProductService productService;
    private static final String EDIT_PAGE = "html/edit-product";

    @Autowired
    public EditPageController(ProductService productService) {
        this.productService = productService;
    }

    // edit-product.html을 SSR로 넘겨주는 핸들러
    @GetMapping("/products/{id}/edit-page")
    public String showEditPage(@PathVariable(name = "id") long id, Model model) {
        ProductDto product = productService.selectProduct(id);

        addAttributesForEditPage(product, model);
        return EDIT_PAGE;
    }

    // edit-product.html에서 보여줄 attributes를 넣는 함수
    private void addAttributesForEditPage(ProductDto product, Model model) {
        // 수정해야 할 제품 넣어줌
        model.addAttribute("productDto", product);
    }
}
