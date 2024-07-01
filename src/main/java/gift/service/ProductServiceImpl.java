package gift.service;

import gift.database.JdbcProductRepository;
import gift.dto.ProductDTO;
import gift.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final JdbcProductRepository jdbcProductRepository;

    public ProductServiceImpl(JdbcProductRepository jdbcProductRepository) {
        this.jdbcProductRepository = jdbcProductRepository;
    }

    @Override
    public List<ProductDTO> readAll() {
        var products = jdbcProductRepository.findAllProducts();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for(var product : products) { //DTO로 전환
            productDTOList.add(new ProductDTO(product));
        }

        return productDTOList;
    }

    //새로운 상품 추가
    @Override
    public void create(ProductDTO prod) {
        jdbcProductRepository.insertProduct(new Product(null,prod.getName(),prod.getPrice(),prod.getImageUrl()));
    }


    @Override
    public void updateName(long id, String name) {
        var prod = jdbcProductRepository.getProduct(id);
        prod.setName(name);
        jdbcProductRepository.updateProduct(id,prod);

    }

    @Override
    public void updatePrice(long id, int price) {
        var prod = jdbcProductRepository.getProduct(id);
        prod.setPrice(price);
        jdbcProductRepository.updateProduct(id,prod);
    }

    @Override
    public void updateImageUrl(long id, String url) {
        var prod = jdbcProductRepository.getProduct(id);
        prod.setImageUrl(url);
        jdbcProductRepository.updateProduct(id,prod);
    }

    @Override
    public void delete(long id) {
        jdbcProductRepository.deleteProduct(id);
    }
}
