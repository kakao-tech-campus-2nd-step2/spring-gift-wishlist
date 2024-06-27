package gift.web.controller.view;

import gift.converter.ProductToUpdateFormDtoConverter;
import gift.domain.Product;
import gift.service.ProductService;
import gift.web.dto.form.CreateProductFormDto;
import gift.web.dto.form.UpdateProductFormDto;
import gift.web.dto.response.ReadAllProductsResponse;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String readAdminPage(Model model) {
        ReadAllProductsResponse allProductsResponse = productService.readAllProducts();
        List<Product> products = allProductsResponse.getProducts();
        model.addAttribute("products", products);
        return "admin";
    }

    @GetMapping("/product/add")
    public String addForm(Model model) {
        model.addAttribute("product", new CreateProductFormDto());
        return "form/add-product-form";
    }

    @GetMapping("/product/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        Product product = productService.searchProduct(id);
        ProductToUpdateFormDtoConverter converter = new ProductToUpdateFormDtoConverter();
        UpdateProductFormDto updateProductFormDto = converter.convert(product);
        model.addAttribute("product", updateProductFormDto);
        return "form/edit-product-form";
    }
}
