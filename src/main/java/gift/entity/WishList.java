package gift.entity;

import java.util.ArrayList;
import java.util.List;

public class WishList {

    private Long id;
    private Long userId;
    private String name;
    private List<Product> products = new ArrayList<>();

    // setter 사용 금지!
    public WishList(Long id, Long userId, String name) {}

    public WishList(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    // ID 반환 메서드
    public Long getId() {
        return id;
    }

    // User ID 반환 메서드
    public Long getUserId() {
        return userId;
    }

    // 이름 반환 메서드
    public String getName() {
        return name;
    }

    // 불변의 제품 리스트 반환 메서드
    public List<Product> getProducts() {
        return products;
    }

    // Products 리스트를 조작하는 메서드
    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}