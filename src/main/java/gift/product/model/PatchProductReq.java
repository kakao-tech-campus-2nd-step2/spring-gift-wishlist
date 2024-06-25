package gift.product.model;

public record PatchProductReq(
        Long id,
        String name,
        Integer price,
        String imageUrl
) {
    public PatchProductReq(Long id, String name, Integer price, String imageUrl) {
        // 초기화 블록에서 필수 값인 id가 null인지 체크
        if (id == null) {
            throw new IllegalArgumentException("수정 요청에서 productId는 필수 값입니다.");
        }
        this.id = id;
        this.price = price;
        this.name = (name != null && name.isEmpty()) ? null : name;
        this.imageUrl = (imageUrl != null && imageUrl.isEmpty()) ? null : imageUrl;
    }
}