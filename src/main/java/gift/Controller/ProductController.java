package gift.Controller;

import gift.Service.ProductService;
import gift.DTO.ProductDTO;
import gift.Model.Product;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 상품 추가
     *
     * @param productDTO
     * @return 결과 메시지
     */
    @PostMapping
    @ResponseBody
    public String postProduct(@ModelAttribute ProductDTO productDTO) {
        String query = productService.postProduct(productDTO);
        return query;
    }

    /**
     * 전체 상품 목록 조회
     *
     * @return products (상품 목록)
     */
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }


    /**
     * 상품 수정
     *
     * @param id
     * @param productDTO
     * @return product (수정된 상품 정보)
     */
    @PutMapping("/{id}")
    @ResponseBody
    public String updateProduct(@PathVariable("id") Long id,
        @RequestBody ProductDTO productDTO) {
        String message = productService.updateProduct(id, productDTO);
        return message;
    }


    /**
     * 해당 ID 리스트에 속한 상품 삭제
     *
     * @param productIds
     * @return
     */
    @DeleteMapping
    @ResponseBody
    public String deleteSelectedProducts(@RequestBody List<Long> productIds) {
        String message = productService.deleteProductsByIds(productIds);
        return message;
    }

    /**
     * 해당 ID 를 가진 상품 삭제
     *
     * @param id
     * @return product (삭제된 상품 정보)
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }


}
