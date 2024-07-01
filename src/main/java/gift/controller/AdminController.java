package gift.controller;

import gift.controller.dto.ProductRequestDto;
import gift.controller.dto.ProductResponseDto;
import gift.model.Product;
import gift.model.ProductDao;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/list")
public class AdminController {

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<ProductResponseDto> productList = productDao.selectAllProduct()
            .stream()
            .map(Product::toProductResponseDto)
            .collect(Collectors.toList());
        model.addAttribute("productList", productList);
        return "list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new ProductResponseDto());
        return "product-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductRequestDto productRequestDto) {
        productDao.insertProduct(productRequestDto.toEntity());
        return "redirect:/admin/list";
    }

    @GetMapping("/edit/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productDao.selectProductById(id));
        return "product-form";
    }

    @PostMapping("edit/{id}")
    public String updateProduct(@PathVariable("id") Long id,
        @ModelAttribute ProductRequestDto productRequestDto) {
        productDao.updateProductById(id, productRequestDto.toEntity());
        return "redirect:/admin/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productDao.deleteProductById(id);
        return "redirect:/admin/list";
    }
}
