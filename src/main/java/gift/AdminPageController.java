package gift;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminPageController {
    private final ProductRepository productRepository = ProductRepository.getInstance();
    private final Integer pageSize = 2;

    @GetMapping(path = "/admin/{page}")
    public String adminPage(Model model, @PathVariable("page") Integer page) {
        List<Product> totalProducts = productRepository.getAllProduct();
        List<Product> subProducts = totalProducts.subList((page - 1) * pageSize,
                Math.min(page * pageSize, totalProducts.size()));

        model.addAttribute("products", subProducts);
        model.addAttribute("page", page);
        model.addAttribute("totalProductsSize", totalProducts.size());
        model.addAttribute("currentPageProductSize", subProducts.size());

        Integer endPage = Math.max(Math.ceilDiv(totalProducts.size(), pageSize), 1);
        Integer startPage = (Math.floorDiv(page - 1, 5) + 1) * 5 - 4;

        List<Integer> pageList = IntStream.rangeClosed(
                startPage,
                Math.min(startPage + 4, endPage))
                .boxed().toList();
        model.addAttribute("pageList", pageList);

        return "adminPage";
    }
}
