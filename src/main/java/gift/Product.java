package gift;

public record Product(Long id, String name, Integer price, String imageUrl) {
    public Product {
        // 빈 객체 전달을 위한 검증 임시 주석처리
//        if(id == null) {
//            throw new IllegalArgumentException("[ERROR] ID는 비워둘 수 없습니다.");
//        }
    }
}
