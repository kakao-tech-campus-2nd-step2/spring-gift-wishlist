package gift.presentation;

import gift.application.ProductService;
import gift.domain.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class EmployeeManageController {

    private final ProductService productService;

    @Autowired
    public EmployeeManageController(ProductService employeeService) {
        this.productService = employeeService;
    }

    @GetMapping("")
    public String getEmployees(Model model) {
        List<Product> productList = productService.getProduct();
        model.addAttribute("products", productList);
        model.addAttribute("newProduct", new CreateEmployeeRequestDTO("", 0.0, ""));
        model.addAttribute("pageSize", productList.size());
        model.addAttribute("totalEntries", productList.size());
        return "wishlist.html";
    }

    @PostMapping("")
    public String addEmployee(@ModelAttribute CreateEmployeeRequestDTO createEmployeeRequestDTO) {
        productService.addProduct(createEmployeeRequestDTO);
        return "redirect:/wishlist";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    public record CreateEmployeeRequestDTO(String name, Double price, String imageUrl) {

    }
}
