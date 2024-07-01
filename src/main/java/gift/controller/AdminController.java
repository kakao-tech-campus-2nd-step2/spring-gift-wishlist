package gift.controller;

import gift.model.Product;
import gift.model.ProductDao;
import gift.model.ProductRequest;
import gift.model.ProductResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private ProductDao productDao;
    @Autowired
    AdminController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/admin/product")
    public String registerProductForm() {
        return "addProduct";
    }

    @PostMapping("/admin/product")
    public String registerProduct(ProductRequest productRequest) {
        Product product = productDao.save(productRequest);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products")
    public String getAllProducts(Model model) {
        List<ProductResponse> productList = productDao.findAll()
            .stream().map(Product::toDTO).toList();
        model.addAttribute("products", productList);
        return "productList";
    }

    @GetMapping("/admin/product/{id}")
    public ProductResponse getProduct(@PathVariable("id") Long id) {
        Product product = productDao.findById(id);
        return product.toDTO();
    }

    @GetMapping("/product")
    public String updateProductForm(@RequestParam("id") Long id) {
        return "editProduct";
    }

    @PutMapping("/admin/product/{id}")
    public String updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        Product product = productDao.update(id, productRequest);
        return "redirect:/admin/products";
    }

    @DeleteMapping("/admin/product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productDao.delete(id);
        return "redirect:/admin/products";
    }
}
