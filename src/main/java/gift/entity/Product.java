package gift.entity;

import gift.dto.ProductDTO;
import gift.exception.BadRequestExceptions.BlankContentException;
import java.util.Objects;

public class Product {

    private Integer id;

    private String name;

    private Integer price;

    private String imageUrl;

    private Integer quantity;

    public Product() {
    }

    public Product(Integer id, String name, Integer price, String imageUrl) {
        this(id, name, price, imageUrl, 0);
    }

    public Product(ProductDTO productDTO) {
        this(productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl(), 0);
        blankCatch(productDTO);
    }

    public Product(Integer id, String name, Integer price, String imageUrl, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductDTO convertToProductDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getImageUrl(), product.getQuantity());
    }

    private void blankCatch(ProductDTO productDTO) throws BlankContentException {
        if (productDTO.name().isBlank() || productDTO.price() == null || productDTO.imageUrl()
                .isBlank()) {
            throw new BlankContentException("입력 값에 빈 곳이 있습니다. 다시 입력해주세요");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(imageUrl, product.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
