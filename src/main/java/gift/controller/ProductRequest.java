package gift.controller;


public record ProductRequest(
    String name,
    Integer price,
    String imageUrl
) {

}
