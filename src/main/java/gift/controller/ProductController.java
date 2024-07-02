/**
 * ProductController Class
 * 사용 : Product에 대한 CRUD 처리를 수행하고, 해당 결과를 보여줄 View를 가져온다
 * 기능 : 상품 목록 불러오기, 상품 추가, 삭제, 수정
 */
package gift.controller;

import gift.DTO.Product;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// RestController : 데이터 반환
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    /*
     * 상품 목록 출력
     * GET 요청에 따라 Json 형식 배열을 반환
     */
    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> findProducts() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    /*
     * 상품 추가
     * POST 요청에 따라 다음과 같은 결과 값을 반환
     * 동일 ID 상품이 존재하는 경우 : 상태코드 409 Conflict
     * 동일 ID 상품이 존재하지 않는 경우 : 실제로 DB에 상품을 등록, 상태코드 201 Created
     * + 제한 조건 : 글자수 15자 이하, 특수문자 제한, 제품명에 카카오가 들어가면 경고
     */
    @PostMapping("/api/products")
    public ResponseEntity<Map<String, String>> addProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        List<Long> idList = productService.findAllId();
        for (Long l : idList) {
            if(l.equals(product.getId()))
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        productService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
     * 상품 삭제
     * DELETE 요청에 따라 다음과 같은 결과 값을 반환
     * 삭제 성공 : 200 OK
     */
    @DeleteMapping("/api/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /*
     * 상품 수정
     * PUT 요청에 따라 다음과 같은 결과 값을 반환
     * Product 정보와 리소스 URI가 일치하지 않으면 : 400 BAD_REQUEST
     * 리소스 URI에 대해, 해당 URI가 배정된 객체가 없으면 : 404 NOT_FOUND
     * 수정 성공 : 200 OK
     */
    @PutMapping("/api/products/{productId}")
    public ResponseEntity<Void>modifyProduct(@PathVariable("productId") Long id, Product product){
        if(!id.equals(product.getId())){
            return new ResponseEntity<>((HttpStatus.BAD_REQUEST));
        }

        List<Long> idList = productService.findAllId();
        if(!idList.contains(id)){
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        }

        productService.updateProduct(product, id);
        return new ResponseEntity<>((HttpStatus.OK));
    }
}