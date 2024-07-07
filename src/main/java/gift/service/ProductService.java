package gift.service;

import gift.model.Product;
import gift.repository.ProductRepositoryImpl;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepositoryImpl productRepository;

    public ProductService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }


    public void addProduct(Product product) {
        if (isExistProduct(product)) {
            throw new IllegalStateException("이미 존재하는 상품입니다.");
        }
        if (isInvalidProduct(product)) {
            throw new IllegalArgumentException("상품의 속성이 누락되었습니다.");
        }
        if (isPriceisUnderZero(product)) {
            throw new IllegalArgumentException("가격은 음수가 될 수 없습니다.");
        }
        handleProductNameRestriction(product);
        productRepository.insertProduct(product);
    }

    private void handleProductNameRestriction(Product product) {
        if (isNameLimitExceed(product)) {
            throw new IllegalArgumentException("이름은 15글자 이상이 될 수 없습니다");
        }
        if (isNameHasSpecialCharacter(product)) {
            throw new IllegalArgumentException("상품명은 특수기호 (),[],+,-,&,/,_ 를 제외한 특수 문자 사용이 불가합니다.");
        }
        if (isNameHasKakao(product)) {
            throw new IllegalArgumentException("'카카오' 상표는 MD 협의 후 사용할 수 있습니다");
        }
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> retreiveProduct(Long id) {
        var product = getProduct(id);
        if (product == null) {
            return Optional.empty();
        }
        if (!getAllProducts().containsKey(id)) {
            throw new NoSuchElementException("Product Not Found");
        }
        return Optional.of(getAllProducts().get(id));
    }

    public Map<Long, Product> getAllProducts() {
        return productRepository.selectAllProducts();
    }

    @Transactional
    public void updateProductDetail(Product product) {
        if (isPriceisUnderZero(product)) {
            throw new IllegalArgumentException("가격음 음수가 될 수 없습니다");
        }
        if (isInvalidProduct(product)) {
            throw new IllegalArgumentException("상품의 속성이 누락되었습니다.");
        }
        if (!productRepository.existsById(product.getId())) {
            throw new NoSuchElementException("Product not found with id: " + product.getId());
        }
        handleProductNameRestriction(product);
        productRepository.updateProduct(product);
    }

    @Transactional
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
        productRepository.deleteProduct(id);
    }

    public boolean isProductsRepositoryEmpty() {
        return productRepository.selectAllProducts().isEmpty();
    }


    private boolean isExistProduct(Product product) {
        return productRepository.existsById(product.getId());
    }


    private boolean isInvalidProduct(Product newProduct) {
        return newProduct.getId() == null || newProduct.getId() < 0 || newProduct.getName()
            .isEmpty() || newProduct.getImageUrl().isEmpty();
    }

    private boolean isPriceisUnderZero(Product product) {
        return product.getPrice() < 0;
    }

    private boolean isNameLimitExceed(Product product) {
        return product.getName().length() >= 15;
    }

    private boolean isNameHasSpecialCharacter(Product product) {
        return !product.getName().matches("[ㄱ-ㅎ가-힣a-zA-Z0-9()+\\-&/_\\[\\]]*$");
    }

    private boolean isNameHasKakao(Product product) {
        return product.getName().contains("카카오");
    }


}