package gift.dto;

public record ProductRequest(Long id,
                             String name,
                             int price,
                             String imageUrl) { }