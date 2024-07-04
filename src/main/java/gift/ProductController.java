package gift;

import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * 상품 목록을 보여주는 products.html 을 렌더링하여 반환
     *
     * @return products.html
     */
    @GetMapping("")
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
     * 추가할 상품의 정보를 받아서 저장 <br> 이미 존재하는 상품 id이면 404 Not Found
     *
     * @return HTTP response
     */
    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody @Valid Product product) {
        productDao.selectOneProduct(product.id())
            .ifPresent(v -> {
                throw new IllegalArgumentException("이미 존재하는 ID 입니다.");
            });
        productDao.insertNewProduct(product);
        return ResponseEntity.ok("성공적으로 추가되었습니다!");
    }

    /**
     * 상품 정보를 수정하는 페이지인 editForm.html 반환 <br> 상품의 기존 정보를 model에 담아서 반환
     *
     * @param id 수정할 상품의 id
     * @return editForm.html
     */
    @GetMapping("/product/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productDao.selectOneProduct(id)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 상품입니다."));
        model.addAttribute("product", product);
        return "editForm";
    }

    /**
     * 수정된 상품 정보를 받아서 데이터를 갱신. <br> 수정할 상품이 존재하지 않으면 404 Not Found
     *
     * @return HTTP response
     */
    @PutMapping("/product")
    public ResponseEntity<String> editProduct(@RequestBody @Valid Product product) {
        productDao.selectOneProduct(product.id())
            .orElseThrow(() -> new NoSuchElementException("수정할 상품이 존재하지 않습니다."));
        productDao.updateProduct(product);
        return ResponseEntity.ok("수정되었습니다!");
    }

    /**
     * 상품을 삭제. <br> 해당 상품이 존재하지 않으면 404 Not Found
     *
     * @param id 삭제할 상품의 id
     * @return HTTP response
     */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productDao.selectOneProduct(id)
            .orElseThrow(() -> new NoSuchElementException("삭제할 상품이 존재하지 않습니다."));
        productDao.deleteProduct(id);
        return ResponseEntity.ok("삭제되었습니다!");
    }
}
