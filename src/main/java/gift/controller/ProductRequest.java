package gift.controller;


public record ProductRequest(
    String name,
    int price,
    String imageUrl
) {

}
