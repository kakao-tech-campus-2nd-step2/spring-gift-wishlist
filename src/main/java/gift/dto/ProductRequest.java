package gift.dto;

public record ProductRequest(String name,
                             int price,
                             String imageUrl) { }