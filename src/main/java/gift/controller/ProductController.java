package gift.controller;

//import gift.exception.ResourceNotFoundException;
import gift.model.Product;
import gift.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String retreiveAllProducts(Model model) {
        if (!productService.isProductsRepositoryEmpty()) {
            model.addAttribute("productDto", productService.getAllProducts().values());
        }
        return "getproducts";
    }

    @GetMapping("/product/{id}")
    public String retreiveProduct(@PathVariable(name = "id") Long id, Model model) {
        Optional<Product> product = productService.retreiveProduct(id);
        model.addAttribute("productDto", product);
        return "getproducts";
    }

    @GetMapping("/product/add/form")
    public String registerProductForm(Model model) {
        model.addAttribute("productDto", new Product());
        return "addproductform";
    }


    @PostMapping("/product/add")
    public String registerProduct(@ModelAttribute("productDto") Product product,
        HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            productService.addProduct(product);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/product/add/form";
        }
    }

    @GetMapping("/product/update/form")
    public String updateProductForm(Model model) {
        model.addAttribute("productDto", new Product());
        return "updateproductform";
    }


    @PostMapping("/product/update")
    public String updateProductsName(@ModelAttribute(name = "productDto") Product product,
        HttpServletResponse httpServletResponse,  RedirectAttributes redirectAttributes) {
        try {
            productService.updateProductDetail(product);
            return "redirect:/";
        } catch (NoSuchElementException | IllegalArgumentException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/product/update/form";
        }
    }


    @GetMapping("/product/delete/form")
    public String deleteProductForm(Model model) {
        model.addAttribute("productDto", new Product());
        return "deleteproduct";
    }

    @GetMapping("/product/delete")
    public String deleteProduct(@ModelAttribute(name = "productDto") Product product,
        HttpServletResponse httpServletResponse,  RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProductById(product.getId());
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/product/delete/form";
        }
    }

}


