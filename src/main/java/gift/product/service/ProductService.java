package gift.product.service;

import gift.product.dto.ProductDto;
import gift.product.entity.ProductEntity;
import gift.product.repository.ProductDao;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 아직은 로직이 단순해서 service가 필요 없는 규모라고 생각하지만, 확장을 위해 service로 분리
@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    // dao를 호출해서 productDTO를 DB에 넣는 함수
    public List<ProductDto> insertProduct(ProductDto productDto) {
        productDao.insertProduct(productDto);

        // 반환값은 그 결과
        return selectProducts();
    }

    // dao를 호출해서 DB에 담긴 로우를 반환하는 함수
    public ProductDto selectProduct(long id) {
        ProductEntity productEntity = productDao.selectProduct(id);

        return new ProductDto(productEntity.getId(), productEntity.getName(),
            productEntity.getPrice(), productEntity.getImage(), productEntity.getMd());
    }

    // dao를 호출해서 DB에 담긴 로우들을 반환하는 함수
    public List<ProductDto> selectProducts() {
        // Dao로부터 Entity를 받아서,
        List<ProductEntity> productEntityList = productDao.selectProducts();

        // Entity의 메서드를 사용하여 비즈니스 로직을 수행하고, (지금은 로직이 없습니다.)

        // 이를 토대로 Entity의 값을 사용하여 Dto로 컨트롤러에게 반환하는 구조
        return productEntityList.stream().map(productEntity -> new ProductDto(productEntity.getId(),
            productEntity.getName(), productEntity.getPrice(), productEntity.getImage(),
            productEntity.getMd())).collect(Collectors.toList());
    }

    // dao를 호출해서 특정 로우를 파라메터로 전달된 DTO로 교체하는 함수
    public List<ProductDto> updateProduct(ProductDto productDto) {
        productDao.updateProduct(productDto);

        // 반환값은 그 결과
        return selectProducts();
    }

    // dao를 호출해서 특정 로우를 제거하는 함수
    public List<ProductDto> deleteProduct(long id) {
        productDao.deleteProduct(id);

        // 반환값은 그 결과
        return selectProducts();
    }

    // dao를 호출해서 모든 로우를 제거하는 함수
    public List<ProductDto> deleteProducts() {
        productDao.deleteProducts();

        // 반환값은 그 결과
        return selectProducts();
    }
}
