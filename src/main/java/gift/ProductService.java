package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService {

    private static final String ADD_SUCCESS_MSG = "상품 추가 성공";
    private static final String UPDATE_SUCCESS_MSG = "상품 수정 성공";
    private static final String DELETE_SUCCESS_MSG = "상품 삭제 성공";

    @Autowired
    private final ProductDao productDao;


    //확인을 위한 initialProduct 생성
    Product initialProduct = new Product(
            8146027L,
            "아이스 카페 아메리카노 T",
            4500,
            "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
    );

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
        productDao.insertProduct(initialProduct);
    }


    public Product getOneProduct(Long productId) {
        return productDao.selectOneProduct(productId);
    }

    public Collection<Product> getProduct() {
        return productDao.selectAllProducts();
    }

    public String addNewProduct(Product newProduct) {
        productDao.insertProduct(newProduct);
        return ADD_SUCCESS_MSG;
    }

    public String updateProductInfo(Long productId, Product product) {

        Product productToUpdate = productDao.selectOneProduct(productId);

        if (product.getName() != null) {
            productToUpdate.setName(product.getName());
        }
        if (product.getPrice() > 0) {
            productToUpdate.setPrice(product.getPrice());
        }
        if (product.getImageUrl() != null) {
            productToUpdate.setImageUrl(product.getImageUrl());
        }

        productDao.updateProduct(productToUpdate);

        return UPDATE_SUCCESS_MSG;
    }

    public String deleteTheProduct(Long productId) {
        productDao.deleteProduct(productId);
        return DELETE_SUCCESS_MSG;
    }

}
