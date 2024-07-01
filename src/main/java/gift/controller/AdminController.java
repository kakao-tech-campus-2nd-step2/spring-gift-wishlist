package gift.controller;

import gift.common.exception.ProductNotFoundException;
import gift.controller.dto.request.ProductRequest;
import gift.model.Product;
import gift.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductRepository productRepository;

    @Autowired
    public AdminController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll().stream().toList();
        model.addAttribute("products", products);
        return "admin/list";
    }

    @GetMapping("/products/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.find(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));

        model.addAttribute("product", product);

        return "admin/edit";
    }

    @PostMapping("/products")
    public String productSave(@RequestBody ProductRequest newProduct) {
        productRepository.save(newProduct.toModel());

        return "admin/list";
    }

    @PatchMapping("/products/{id}")
    public String productModify(@PathVariable("id") Long id,
                                @RequestBody ProductRequest modifyProduct) {
        Product findedProduct = productRepository.find(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));

        productRepository.save(modifyProduct.toModel(id));

        return "admin/list";
    }

    @DeleteMapping("/products/{id}")
    public String productDelete(@PathVariable("id") Long id) {
        Product findedProduct = productRepository.find(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));

        findedProduct.delete();
        productRepository.save(findedProduct);

        return "admin/list";
    }

}
