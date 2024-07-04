package gift.controller;

import gift.DTO.Product;
import gift.constants.ErrorMessage;
import gift.constants.SuccessMessage;
import gift.repository.ProductDao;
import jakarta.validation.Valid;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/product")
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * 추가할 상품의 정보를 받아서 저장 <br> 이미 존재하는 상품 id이면 IllegalArgumentException
     *
     * @return HTTP response
     */
    @PostMapping()
    public ResponseEntity<String> addProduct(@RequestBody @Valid Product product) {
        productDao.selectOneProduct(product.id())
            .ifPresent(v -> {
                throw new IllegalArgumentException(ErrorMessage.ID_ALREADY_EXISTS_MSG);
            });
        productDao.insertNewProduct(product);
        return ResponseEntity.ok(SuccessMessage.ADD_PRODUCT_SUCCESS_MSG);
    }

    /**
     * 수정된 상품 정보를 받아서 데이터를 갱신. <br> 수정할 상품이 존재하지 않으면 NoSuchElementException
     *
     * @return HTTP response
     */
    @PutMapping()
    public ResponseEntity<String> editProduct(@RequestBody @Valid Product product) {
        productDao.selectOneProduct(product.id())
            .orElseThrow(() -> new NoSuchElementException(ErrorMessage.PRODUCT_NOT_EXISTS_MSG));
        productDao.updateProduct(product);
        return ResponseEntity.ok(SuccessMessage.EDIT_PRODUCT_SUCCESS_MSG);
    }

    /**
     * 상품을 삭제. <br> 해당 상품이 존재하지 않으면 NoSuchElementException
     *
     * @param id 삭제할 상품의 id
     * @return HTTP response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productDao.selectOneProduct(id)
            .orElseThrow(() -> new NoSuchElementException(ErrorMessage.PRODUCT_NOT_EXISTS_MSG));
        productDao.deleteProduct(id);
        return ResponseEntity.ok(SuccessMessage.DELETE_PRODUCT_SUCCESS_MSG);
    }
}
