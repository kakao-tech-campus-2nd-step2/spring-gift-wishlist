package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping()
    public String view(Model model) {
        model.addAttribute("products", productDao.getAllProducts());
        model.addAttribute("productDto", new ProductDto());
        return "administrator";
    }

    @PostMapping("/add")
    public RedirectView add(@ModelAttribute("productDto") ProductDto productDto) {
        productDao.insert(productDto);
        return new RedirectView("/api/products");
    }

    @PutMapping("/update/{id}")
    public RedirectView update(@PathVariable("id") long id, ProductDto productDto) {
        productDao.update(id, productDto);
        return new RedirectView("/api/products");
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") long id) {
        productDao.delete(id);
        return new RedirectView("/api/products");
    }
}
