package gift.domain;

import org.springframework.lang.NonNull;

public class Product {

  private Product() {
  }

  public static class CreateProduct {

    @NonNull
    private String name;
    @NonNull
    private Integer price;
    @NonNull
    private String imageUrl;

    public CreateProduct(@NonNull String name, @NonNull Integer price,
        @NonNull String imageUrl) {
      this.name = name;
      this.price = price;
      this.imageUrl = imageUrl;
    }

    @NonNull
    public String getName() {
      return name;
    }

    @NonNull
    public Integer getPrice() {
      return price;
    }

    @NonNull
    public String getImageUrl() {
      return imageUrl;
    }
  }

  public static class UpdateProduct {

    @NonNull
    private String name;
    @NonNull
    private Integer price;
    @NonNull
    private String imageUrl;

    public UpdateProduct(@NonNull String name, @NonNull Integer price,
        @NonNull String imageUrl) {
      this.name = name;
      this.price = price;
      this.imageUrl = imageUrl;
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
  }

  public static class ProductSimple {

    private Long id;
    private String name;

    public ProductSimple(Long id, String name) {
      this.id = id;
      this.name = name;
    }

    public Long getId() {
      return id;
    }

    public String getName() {
      return name;
    }
  }
}
