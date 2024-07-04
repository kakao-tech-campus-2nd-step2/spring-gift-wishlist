package gift.service;

import gift.error.AlreadyExistsException;
import gift.error.DBOperationException;
import gift.error.InvalidInputException;
import gift.error.NotFoundException;
import gift.model.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //전체 상품 조회 기능
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //단일 상품 조회 기능
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);
        checkNotFound(product);
        return product;
    }

    public List<Product> searchProduct(String name) {
        return productRepository.findByCond(name);
    }

    //상품 추가 기능
    public void addProduct(Product product) {
        checkInvalidInput(product);
        checkAlreadyExists(product);
        checkDBOperation(productRepository.save(product));
    }

    //상품 수정 기능
    public void updateProduct(Long id, Product product) {
        checkInvalidInput(product);
        checkAlreadyExists(product);
        checkDBOperation(productRepository.update(id, product));
    }

    //상품 삭제 기능
    public void deleteProduct(Long id) {
        checkDBOperation(productRepository.delete(id));
    }

    private void checkAlreadyExists(Product product) {
        for (Product p : productRepository.findAll()) {
            if (p.isEqual(product)) {
                throw new AlreadyExistsException("해당 상품이 이미 존재 합니다!");
            }
        }
    }

    private void checkInvalidInput(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new InvalidInputException("상품 이름을 입력하세요!");
        }
        if (product.getPrice() == null) {
            throw new InvalidInputException("상품 가격을 입력하세요");
        }
        if (product.getPrice() <= 0) {
            throw new InvalidInputException("상품 가격은 0 이하가 될 수 없습니다. 다시 입력하세요");
        }
        if (product.getImageUrl() == null || product.getImageUrl().isBlank()) {
            throw new InvalidInputException("상품 이미지URL을 입력하세요!");
        }
        if (!validImageUrl(product.getImageUrl())) {
            throw new InvalidInputException(
                "상품 이미지URL은 웹주소 형식으로 입력하세요. \n 예시) https://......image.(jpg,gif,png)");
        }
    }

    private void checkNotFound(Product product) {
        if (product == null) {
            throw new NotFoundException("해당 상품이 존재하지 않습니다.");
        }
    }

    private void checkDBOperation(int result) {
        if (result <= 0) {
            throw new DBOperationException("DB 연산 실패!!");
        }
    }

    private boolean validImageUrl(String imageUrl) {
        String IMAGE_URL_REGEX = "^(http(s?):)([/|.\\w|\\s|-])*\\.(?:jpg|gif|png)$";
        Pattern pattern = Pattern.compile(IMAGE_URL_REGEX);

        Matcher matcher = pattern.matcher(imageUrl);
        return matcher.matches();
    }

}
