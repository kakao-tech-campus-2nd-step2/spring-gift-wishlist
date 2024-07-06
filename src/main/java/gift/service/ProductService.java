package gift.service;

import gift.dto.CreateProduct;
import gift.dto.EditProduct;
import gift.entity.Product;
import gift.repository.ProductDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@ControllerAdvice
@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Long createProduct(CreateProduct.Request request) {
        checkValidProductName(request.getName());
        return productDao.insert(request);
    }

    public List<Product> getAll() {
        return productDao.findAll();
    }

    public Product getOneById(int id) {
        return productDao.findOneById(id);
    }

    public void update(int id, EditProduct.Request request) {
        checkValidProductName(request.getName());
        productDao.update(id, request);
    }

    public void delete(int id) {
        productDao.delete(id);
    }


    // 이름 유효성 검사 코드
    public boolean checkValidProductName(String name) {
        boolean result = false;
        if (!(checkValidLength(name, 1, 15))) {
            throw new IllegalProductNameLengthException();
        }
        if (!(checkValidSpecialCharacter(name))) {
            throw new IllegalProductNameCharacterException();
        }
        if (!(checkNotContainKeyword(name, "카카오"))) {
            throw new IllegalProductNameKeywordException();
        }
        result = true;
        return result;

    }

    private boolean checkValidLength(String name, int start, int end) {
        return (name.length() >= 1 && name.length() <= end);
    }

    private boolean checkValidSpecialCharacter(String name) {
        boolean result = true;

        char[] nameWords = name.toCharArray();
        for (char word : nameWords) {
            if (checkSpecialCharacter(word) && (!checkAllowedSpecialCharacter(word))) {
                result = false;
            }
        }
        return result;
    }

    private boolean checkAllowedSpecialCharacter(char word) {
        char[] allowedSpecialCharacters = {'(', ')', '[', ']', '+', '-', '&', '/', '_'};
        for (int allowedSpecialCharacter : allowedSpecialCharacters) {
            if (word == allowedSpecialCharacter) {
                return true;
            }
        }
        return false;
    }

    private boolean checkSpecialCharacter(char word) {
        boolean result = true;
        if ((word >= 'a' && word <= 'z') ||
                (word >= 'A' && word <= 'Z') ||
                (word >= '0' && word <= '9') ||
                (word >= '가' && word <= '힣') ||
                (word == ' ')) {
            result = false;
        }
        return result;
    }

    private boolean checkNotContainKeyword(String name, String keyword) {
        return !(name.contains(keyword));
    }

    public class IllegalProductNameLengthException extends IllegalArgumentException {
        public IllegalProductNameLengthException() {
            super("상품 이름은 1 ~ 15 글자로 입력해주세요.");
        }
    }

    public class IllegalProductNameCharacterException extends IllegalArgumentException {
        public IllegalProductNameCharacterException() {
            super("상품 이름에서 특수문자는 ()[] + - & / _ 만 사용 가능합니다.");
        }
    }

    public class IllegalProductNameKeywordException extends IllegalArgumentException {
        public IllegalProductNameKeywordException() {
            super("상품이름에 \"카카오\" 키워드를 포함시키고 싶으신 경우, 담당MD와의 협의가 필요합니다. 고객센터로 문의주세요.");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalProductNameLengthException.class, IllegalProductNameCharacterException.class, IllegalProductNameKeywordException.class})
    public ResponseEntity<String> handleProductNameExceptions(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


//    public Map<Long,ProductDTO> getAllProducts() {
//        Map<Long,Product> allProducts = collectionDB.findAll();
//        Map<Long,ProductDTO> allProductsDTO= new HashMap<>();
//        for (Long key : allProducts.keySet()) {
//            Product product = allProducts.get(key);
//            ProductDTO productDTO = new ProductDTO(product.getName(),product.getPrice(),product.getUrl());
//            allProductsDTO.put(key,productDTO);
//        }
//        return allProductsDTO ;
//    }
//
//
//    public ProductDetailDTO getProductDetail(Long id) {
//        return ProductDetailDTO.fromEntity(collectionDB.getProducts().get(id));
//    }
//
//    public ProductDetailDTO editProductDetail(Long id, EditProduct.Request request) {
//        Product product = collectionDB.getProducts().get(id);
//        product.setName(request.getName());
//        product.setPrice(request.getPrice());
//        product.setUrl(request.getImageUrl());
//        return ProductDetailDTO.fromEntity(product);
//    }
//
//    public ProductDetailDTO deleteProduct(Long id) {
//        Product product = collectionDB.getProducts().get(id);
//        ProductDetailDTO productDetailDTO= new ProductDetailDTO(product.getName(), product.getPrice(), product.getUrl());
//        collectionDB.getProducts().remove(id);
//        return  productDetailDTO;
//    }
//

}
