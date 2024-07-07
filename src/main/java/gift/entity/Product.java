package gift.entity;

// 관리할 객체 만들기
public class Product {
    public long id;
    public String name;
    public int price;
    public String imgUrl;

    // 기본 생성자 (필드 초기화는 update 메서드에서 처리)
    public Product() {}

    // 생성자 - id를 포함하지 않고 초기화
    public Product(String name, int price, String imgUrl) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    // 생성자 - id를 포함하여 초기화
    public Product(long id, String name, int price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    // 필드 업데이트 메서드
    public void update(Long id, String name, int price, String imgUrl) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    // id 필드의 getter
    public long id() {
        return id;
    }

    // name 필드의 getter
    public String name() {
        return name;
    }

    // price 필드의 getter
    public int price() {
        return price;
    }

    // imgUrl 필드의 getter
    public String imgUrl() {
        return imgUrl;
    }

    // toString 메서드 오버라이드
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}