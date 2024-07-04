package gift.dto;

// dto를 record로 리팩토링
public record ProductDto(
    long id,
    String name,
    int price,
    String image,
    boolean md) {

    // md의 t/f 여부에 따라 Yes/No로 반환하는 메서드. view를 위해 사용
    public String mdToString() {
        if (md) {
            return "Yes";
        }

        return "No";
    }
}
