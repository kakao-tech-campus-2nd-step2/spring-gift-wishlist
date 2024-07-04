package gift.Controller;

import gift.Model.Product;
import gift.Model.ProductDTO;
import gift.Repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final ProductRepository productRepository;

    public AdminController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findProductsAll());
        return "product_list";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("productDTO", new ProductDTO(null, "", 0, ""));
        return "add_product_form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute @Valid ProductDTO productDTO, BindingResult result,
        Model model) {
        if (validateProduct(productDTO, result, model)) {
            model.addAttribute("productDTO", productDTO);
            return "add_product_form";
        }
        productRepository.saveProduct(ProductConverter.toEntity(productDTO));
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findProductsById(id);
        model.addAttribute("productDTO", ProductConverter.toDTO(product));
        return "edit_product_form";
    }

    @PutMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id,
        @ModelAttribute @Valid ProductDTO updatedProductDTO,
        BindingResult result, Model model) {
        if (validateProduct(updatedProductDTO, result, model)) {
            model.addAttribute("productDTO", updatedProductDTO);
            return "edit_product_form";
        }
        productRepository.updateProduct(ProductConverter.toEntity(updatedProductDTO), id);
        return "redirect:/admin/products";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productRepository.deleteProduct(id);
        return "redirect:/admin/products";
    }

    private boolean validateProduct(ProductDTO productDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            if (result.hasFieldErrors("price")) {
                model.addAttribute("priceError", "가격은 숫자만 입력 가능합니다.");
            }
            return true;
        }
        return false;
    }

}
