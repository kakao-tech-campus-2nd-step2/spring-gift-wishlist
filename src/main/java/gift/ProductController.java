package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String add(@ModelAttribute("productDto") ProductDto productDto, Model model) {
        productDao.insert(productDto);
        return view(model);
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, ProductDto productDto, Model model) {
        productDao.update(id, productDto);
        return view(model);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        productDao.delete(id);
        return view(model);
    }
}
