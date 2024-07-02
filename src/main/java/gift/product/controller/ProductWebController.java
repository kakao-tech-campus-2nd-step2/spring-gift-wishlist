package gift.product.controller;

import gift.product.model.HashMapProductRepository;
import gift.product.model.dto.GetProductRes;
import gift.product.model.dto.PatchProductReq;
import gift.product.model.dto.PostProductReq;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
public class ProductWebController {
    private final HashMapProductRepository productRepository = new HashMapProductRepository();
    private static final Logger logger = LoggerFactory.getLogger(ProductWebController.class);

    // 메인 페이지 (상품 목록 페이지)
    @GetMapping
    public String listProducts(Model model) {
        List<GetProductRes> products = productRepository.findAllProduct();
        logger.info("Loaded products: {}", products);
        System.out.println("Loaded products: " + products);
        model.addAttribute("products", products);
        return "products";
    }

    // 상품 추가 페이지
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("productReq", new PostProductReq());
        return "add_product";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute PostProductReq productReq, RedirectAttributes redirectAttributes) {
        logger.info("addProduct : {} {} {}", productReq.getName(), productReq.getPrice(), productReq.getImageUrl());
        productRepository.addProduct(productReq);
        redirectAttributes.addFlashAttribute("message", "새 상품이 추가되었습니다.");
        return "redirect:/product";
    }

    //상품 수정 페이지
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        GetProductRes product = productRepository.findProduct(id);
        model.addAttribute("productReq", product);
        return "edit_product";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute PatchProductReq patchProductReq,
                                RedirectAttributes redirectAttributes) {
        productRepository.updateProduct(patchProductReq);
        redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 수정되었습니다.");
        return "redirect:/product";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteProduct(id);
        return "redirect:/product";
    }

}
