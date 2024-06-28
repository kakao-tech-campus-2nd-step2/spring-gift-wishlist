package gift.Service;

import gift.Repository.ProductRepository;
import gift.dto.ProductDTO;
import gift.model.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {

    private final Map<Long, Product> products;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.products = productRepository.products;
    }

    /**
     * 상품 추가
     *
     * @param productDTO
     * @return 추가 성공 시 추가된 상품 정보, 실패 시 실패 메시지
     */
    public String postProduct(ProductDTO productDTO) {
        Product product = new Product(
            productDTO.getName(),
            productDTO.getPrice(),
            productDTO.getImageUrl()
        );
        if (!existProduct(productDTO.getName())) {
            products.put(product.getId(), product);
            Product.increase();
            return "상품이 성공적으로 추가되었습니다.";
        }
        return "해당 이름의 상품이 이미 존재합니다.";
    }

    /**
     * 상품 목록 전체 조회
     *
     * @return products (상품 목록)
     */
    public Map<Long, Product> getProducts() {
        return products;
    }

    /**
     * 특정 ID 값의 상품 조회
     *
     * @param id
     * @return product (해당 ID 를 가진 상품)
     */
    public ResponseEntity<Object> getProduct(Long id) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 상품이 존재하지 않습니다.");
        }
        return ResponseEntity.ok(product);
    }

    /**
     * 상품 내용 수정
     *
     * @param id
     * @param productDTO
     * @return product (수정된 상품 정보)
     */
    public String updateProduct(Long id, ProductDTO productDTO) {
        Product product = products.get(id);
        if (product == null) {
            return "해당 ID의 상품이 존재하지 않습니다.";
        }
        if (existSameName(id, productDTO.getName())) {
            return "수정할 이름을 가진 상품이 이미 존재합니다. 다른 이름을 입력하세요.";
        }
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl());
        products.put(product.getId(), product);

        return "상품이 수정되었습니다.";
    }

    /**
     * 모든 상품 삭제
     *
     * @return 삭제 완료 메시지
     */
    public ResponseEntity<Object> deleteAllProducts() {
        products.clear();
        return ResponseEntity.ok("모든 상품을 삭제했습니다.");
    }

    /**
     * 해당 ID 를 가진 상품 삭제
     *
     * @param id
     * @return product (삭제된 상품 정보)
     */
    public String deleteProduct(Long id) {
        if (products.get(id) == null) {
            return "해당 ID의 상품이 존재하지 않습니다";
        }
        products.remove(id);
        return "상품이 삭제되었습니다.";
    }

    /**
     * @param name
     * @return 해당 이름을 가진 product 가 상품 목록에 존재하면 true, 그렇지 않으면 false
     */
    public boolean existProduct(String name) {
        for (Product p : products.values()) {
            if (Objects.equals(name, p.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 상품 이름 수정 시, 다른 상품들 중 해당 이름을 가진 상품이 있는지 확인
     *
     * @param id, name
     * @return 상품 동일한 이름을 가진 product 가 이미 상품 목록에 존재하면 false, 그렇지 않으면 true
     */
    public boolean existSameName(Long id, String name) {
        for (Product p : products.values()) {
            if (Objects.equals(name, p.getName()) && p.getId() != id) {
                return true;
            }
        }
        return false;
    }

    /**
     * 해당 ID 리스트에 속한 상품들 삭제
     * @param productIds
     * @return 성공 여부 메시지
     */
    public String deleteProductsByIds(List<Long> productIds) {
        for (Long productId : productIds) {
            products.remove(productId);
        }
        return "상품들이 성공적으로 제거되었습니다.";
    }
}
