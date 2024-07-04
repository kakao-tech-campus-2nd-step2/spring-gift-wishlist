package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @Autowired
    private ProductController productController;

    @GetMapping("/products")
    public String viewProductPage(Model model) {
        ResponseEntity<List<Product>> response = productController.getAllProducts();
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            return "error/500"; // 적절한 오류 페이지로 리디렉션
        }
        model.addAttribute("products", response.getBody());
        return "product/index";
    }

    @GetMapping("/products/new")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/new";
    }

    @PostMapping("/products")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error ->
                    model.addAttribute("valid_" + error.getField(), error.getDefaultMessage())
            );
            return "product/new";
        }
        ResponseEntity<Object> response = productController.addProduct(product, bindingResult);
        if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            Map<String, String> errors = (Map<String, String>) response.getBody();
            errors.forEach((key, value) -> model.addAttribute("valid_" + key, value));
            return "product/new";
        }
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        ResponseEntity<List<Product>> response = productController.getAllProducts();
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            return "error/500"; // 적절한 오류 페이지로 리디렉션
        }
        Product product = response.getBody().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (product == null) {
            return "error/404"; // 제품이 없을 경우 404 페이지로 리디렉션
        }
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error ->
                    model.addAttribute("valid_" + error.getField(), error.getDefaultMessage())
            );
            return "product/edit";
        }
        ResponseEntity<Object> response = productController.updateProduct(id, product, bindingResult);
        if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            Map<String, String> errors = (Map<String, String>) response.getBody();
            errors.forEach((key, value) -> model.addAttribute("valid_" + key, value));
            return "product/edit";
        }
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productController.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/products/{productId}/options/new")
    public String showNewOptionForm(@PathVariable Long productId, Model model) {
        ResponseEntity<List<Product>> response = productController.getAllProducts();
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            return "error/500"; // 적절한 오류 페이지로 리디렉션
        }
        Product product = response.getBody().stream().filter(p -> p.getId().equals(productId)).findFirst().orElse(null);
        if (product == null) {
            return "error/404"; // 제품이 없을 경우 404 페이지로 리디렉션
        }
        Option option = new Option();
        option.setId((long) (product.getOptions().size() + 1));
        model.addAttribute("option", option);
        model.addAttribute("productId", productId);
        return "option/new";
    }

    @PostMapping("/products/{productId}/options")
    public String saveOption(@PathVariable Long productId, @ModelAttribute("option") Option option, Model model) {
        ResponseEntity<List<Product>> response = productController.getAllProducts();
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            return "error/500"; // 적절한 오류 페이지로 리디렉션
        }
        Product product = response.getBody().stream().filter(p -> p.getId().equals(productId)).findFirst().orElse(null);
        if (product != null) {
            product.addOption(option);
            productController.updateProduct(productId, product, null);
        }
        return "redirect:/products/edit/" + productId;
    }

    @GetMapping("/products/{productId}/options/edit/{optionId}")
    public String showEditOptionForm(@PathVariable Long productId, @PathVariable Long optionId, Model model) {
        ResponseEntity<List<Product>> response = productController.getAllProducts();
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            return "error/500"; // 적절한 오류 페이지로 리디렉션
        }
        Product product = response.getBody().stream().filter(p -> p.getId().equals(productId)).findFirst().orElse(null);
        if (product == null) {
            return "error/404"; // 제품이 없을 경우 404 페이지로 리디렉션
        }
        Option option = product.getOptions().stream().filter(o -> o.getId().equals(optionId)).findFirst().orElse(null);
        if (option == null) {
            return "error/404"; // 옵션이 없을 경우 404 페이지로 리디렉션
        }
        model.addAttribute("option", option);
        model.addAttribute("productId", productId);
        return "option/edit";
    }

    @PostMapping("/products/{productId}/options/{optionId}")
    public String updateOption(@PathVariable Long productId, @PathVariable Long optionId, @ModelAttribute("option") Option option, Model model) {
        productController.updateOption(productId, optionId, option);
        return "redirect:/products/edit/" + productId;
    }

    @GetMapping("/products/{productId}/options/delete/{optionId}")
    public String deleteOption(@PathVariable Long productId, @PathVariable Long optionId) {
        productController.deleteOption(productId, optionId);
        return "redirect:/products/edit/" + productId;
    }
}
