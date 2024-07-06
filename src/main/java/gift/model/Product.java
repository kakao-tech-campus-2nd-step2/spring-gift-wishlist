package gift.model;

import gift.exception.ProductErrorCode;
import gift.exception.ProductException;
import gift.model.dto.ProductRequestDto;

public class Product {

    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product(Long id, String name, int price, String imageUrl) throws ProductException {
        validateKakaoWord(name);
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    private void validateKakaoWord(String name) throws ProductException {
        if (name.contains("카카오")) {
            throw new ProductException(ProductErrorCode.HAS_KAKAO_WORD);
        }
    }
}
