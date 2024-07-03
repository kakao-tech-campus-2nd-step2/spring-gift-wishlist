package gift.service;


import gift.Product;
import gift.ProductDao;

public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public String purchaseProduct(Long id, int amount) {
        Product product = productDao.selectProduct(id);
        if (product.amount() >= amount) {
            productDao.purchaseProduct(id, amount);
            return "Purchase successful";
        }
        return "Not enough stock";
    }
}
