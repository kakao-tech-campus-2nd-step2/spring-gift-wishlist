package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private ProductController productController;

    @GetMapping("/products")
    public String viewProductPage(Model model) {
        List<Product> products = productController.getAllProducts().getBody();
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/products/new")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/new";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productController.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productController.getAllProducts().getBody()
                .stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product) {
        productController.updateProduct(id, product);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productController.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/products/{productId}/options/new")
    public String showNewOptionForm(@PathVariable Long productId, Model model) {
        Product product = productController.getAllProducts().getBody()
                .stream().filter(p -> p.getId().equals(productId)).findFirst().orElse(null);
        Option option = new Option();
        if (product != null) {
            option.setId((long) (product.getOptions().size() + 1));
        }
        model.addAttribute("option", option);
        model.addAttribute("productId", productId);
        return "option/new";
    }

    @PostMapping("/products/{productId}/options")
    public String saveOption(@PathVariable Long productId, @ModelAttribute("option") Option option) {
        Product product = productController.getAllProducts().getBody()
                .stream().filter(p -> p.getId().equals(productId)).findFirst().orElse(null);
        if (product != null) {
            product.addOption(option); // 상품에 옵션 추가
            productController.updateProduct(productId, product); // 상품 업데이트
        }
        return "redirect:/products/edit/" + productId;
    }

    @GetMapping("/products/{productId}/options/edit/{optionId}")
    public String showEditOptionForm(@PathVariable Long productId, @PathVariable Long optionId, Model model) {
        Product product = productController.getAllProducts().getBody()
                .stream().filter(p -> p.getId().equals(productId)).findFirst().orElse(null);
        if (product != null) {
            Option option = product.getOptions().stream().filter(o -> o.getId().equals(optionId)).findFirst().orElse(null);
            model.addAttribute("option", option);
            model.addAttribute("productId", productId);
        }
        return "option/edit";
    }

    @PostMapping("/products/{productId}/options/{optionId}")
    public String updateOption(@PathVariable Long productId, @PathVariable Long optionId, @ModelAttribute("option") Option option) {
        productController.updateOption(productId, optionId, option); // 옵션 업데이트
        return "redirect:/products/edit/" + productId;
    }

    @GetMapping("/products/{productId}/options/delete/{optionId}")
    public String deleteOption(@PathVariable Long productId, @PathVariable Long optionId) {
        productController.deleteOption(productId, optionId); // 옵션 삭제
        return "redirect:/products/edit/" + productId;
    }

}
