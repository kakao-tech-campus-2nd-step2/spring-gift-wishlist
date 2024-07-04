package gift.entity;

// 상품의 정보를 담는 클래스
public class ProductEntity {
    private long id;
    private String name;
    private int price;
    private String image;
    private boolean md;

    public ProductEntity(long id, String name, int price, String image, boolean md) {
        setId(id);
        setName(name);
        setPrice(price);
        setImage(image);
        setMd(md);
    }

    // getter는 JSON으로 바꿀 수 있도록 public으로 선언
    public long getId() {
        return id;
    }

    // setter는 다른 클래스에서 사용을 지양하기 위해 private으로 선언
    private void setId(long id) {
        // id의 도메인 규칙 검사. setter에 넣음으로써 항상 검사하도록 함.
        verifyId(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    private void setPrice(int price) {
        // price의 도메인 규칙 검사.
        verifyPrice(price);
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    private void setImage(String image) {
        this.image = image;
    }

    public boolean getMd() {
        return md;
    }

    private void setMd(boolean md) {
        this.md = md;
    }

    // id 값을 검사하는 도메인 규칙
    private void verifyId(long id) {
        // 음수인 id는 불가능하다.
        if (id < 0) {
            throw new IllegalArgumentException("id는 음수일 수 없습니다.");
        }
    }

    // price 값을 검사하는 도메인 규칙
    // 비록 지금은 로직이 Id와 동일하지만, 후에 바뀔 수도 있으므로 분리
    private void verifyPrice(int price) {
        // 음수인 price는 불가능하다.
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }
    }
}
