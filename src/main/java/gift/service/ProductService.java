package gift.service;

import gift.dto.CreateProduct;
import gift.entity.Product;
import gift.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Long createProduct(CreateProduct.Request request) {
        return productDao.insert(request);
    }

    public List<Product> getAll() {
        return productDao.findAll();
    }

    public Product getOneById(int id) {
        return productDao.findOneById(id);
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
//    public boolean checkValidProductName(String name) {
//        boolean result = false;
//        if (!(checkValidLength(name,1,15))){
//            throw new IllegalProductNameLengthException(); // 여기에 "상품 이름은 1 ~ 15 글자로 입력해주세요." 값 넣으면 argument 개수가 맞지 않는다고 뜹니다...줌 강의랑 똑같이 했는데 그러네요 ㅡㅜ
//        }
//        if (!(checkValidSpecialCharacter(name))){
//            throw new IllegalProductNameCharacterException();
//        }
//        if (!(checkNotContainKeyword(name,"카카오"))){ //이렇게 이중부정 써서 구현하는거 별로인가요.. 이렇게한이유 : 카카오 포함하지 않는게 정상 -> 정상이 아닌상황에는 예외 발생
//            throw new IllegalProductNameKeywordException();
//        }
//        result =true;
//        return result;
//
//    }
//
//    private boolean checkValidLength(String name,int start, int end) {
//        return (name.length()>=1 && name.length()<=end);
//    }
//
//    private boolean checkValidSpecialCharacter(String name) {
//        boolean result = true;
//
//        char [] nameWords = name.toCharArray();
//        for (char word : nameWords) {
//            if(checkSpecialCharacter(word) && (!checkAllowedSpecialCharacter(word))){
//                result = false;
//            }
//        }
//        return result;
//    }
//
//    private boolean checkAllowedSpecialCharacter(char word) {
//        char [] allowedSpecialCharacters = {'(',')','[',']','+','-','&','/','_'};
//        for (int allowedSpecialCharacter: allowedSpecialCharacters) {
//            if (word == allowedSpecialCharacter) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean checkSpecialCharacter(char word) {
//        boolean result = true;
//        if ((word >='a' && word <='z') ||
//                (word >='A' && word <='Z') ||
//                (word >='0' && word <='9') ||
//                (word >='가' && word <='힣')) {
//            result= false;
//        }
//        return result;
//    }
//
//    private boolean checkNotContainKeyword(String name, String keyword) {
//        return !(name.contains(keyword));
//    }
//
//
//
//    private class IllegalProductNameLengthException extends RuntimeException {
//
//    }
//
//    private class IllegalProductNameCharacterException extends RuntimeException {
//    }
//
//    private class IllegalProductNameKeywordException extends RuntimeException {
//    }
//
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "상품 이름 길이가 부적절합니다.")
//    @ExceptionHandler(IllegalProductNameLengthException.class)
//    public String handleIllegalProductNameLengthException(IllegalProductNameLengthException e) {
//        return "상품 이름은 1 ~ 15 글자로 입력해주세요.";
//    }
//
//    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "상품이름에 부적절한 특수문자가 포함되어있습니다." )
//    @ExceptionHandler(IllegalProductNameCharacterException.class)
//    public String handleIllegalProductNameCharacterException(IllegalProductNameCharacterException e) {
//        return "상품 이름에서 특수문자는 ()[] + - & / _ 만 사용 가능합니다.";
//    }
//
//    @ResponseStatus(value=HttpStatus.BAD_REQUEST,reason = "상품 이름에 부적절한 키워드가 포함되어 있습니다.")
//    @ExceptionHandler(IllegalProductNameKeywordException.class)
//    public String handleIllegalProductNameKeywordException(IllegalProductNameKeywordException e) {
//        return "상품이름에 \"카카오\" 키워드를 포함시키고 싶으신 경우, 담당MD와의 협의가 필요합니다. 고객센터로 문의주세요.";
//    }
//
}
