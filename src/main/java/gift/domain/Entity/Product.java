package gift.domain.Entity;

import gift.domain.dto.ProductResponseDto;

public record Product(Long id, String name, Long price, String imageUrl) { }
