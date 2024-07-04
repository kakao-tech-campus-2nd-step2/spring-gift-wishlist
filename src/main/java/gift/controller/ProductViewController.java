package gift.controller;

import gift.DTO.Product;
import gift.constants.ErrorMessage;
import gift.repository.ProductDao;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/products")
public class ProductViewController {

    private final ProductDao productDao;

    public ProductViewController(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * 상품 목록을 보여주는 products.html 을 렌더링하여 반환
     *
     * @return products.html
     */
    @GetMapping()
    public String getProducts(Model model) {
        List<Product> products = productDao.selectProducts();
        model.addAttribute("products", products);
        return "products";
    }

    /**
     * 상품을 추가하는 페이지인 addForm.html 반환
     *
     * @return addForm.html
     */
    @GetMapping("/product")
    public String addProductForm(Model model) {
        return "addForm";
    }

    /**
     * 상품 정보를 수정하는 페이지인 editForm.html 반환 <br> 해당 상품이 없으면 NoSuchElementException
     *
     * @param id 수정할 상품의 id
     * @return editForm.html
     */
    @GetMapping("/product/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productDao.selectOneProduct(id)
            .orElseThrow(() -> new NoSuchElementException(ErrorMessage.PRODUCT_NOT_EXISTS_MSG));
        model.addAttribute("product", product);
        return "editForm";
    }
}
