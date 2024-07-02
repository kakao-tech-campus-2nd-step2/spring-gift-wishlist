package gift.domain;

import java.util.Objects;

import gift.dto.ProductResponseDto;

public class Product {
	private Long id;
	private String name;
	private Long price;
	private String imageUrl;

	public static Product of(Long id, String name, Long price, String imageUrl) {
		return new Product(id, name, price, imageUrl);
	}

	public static Product from(ProductResponseDto productResponseDto) {
		return new Product(productResponseDto.id(), productResponseDto.name(), productResponseDto.price(),
			productResponseDto.imageUrl());
	}

	private Product(Long id, String name, Long price, String imageUrl) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imageUrl = imageUrl;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Product product))
			return false;
		return Objects.equals(id, product.id) && Objects.equals(name, product.name)
			&& Objects.equals(price, product.price) && Objects.equals(imageUrl, product.imageUrl);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price, imageUrl);
	}
}
