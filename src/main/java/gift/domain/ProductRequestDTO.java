package gift.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductRequestDTO {

    private String name;

    private Long price;

    private String description;

    public ProductRequestDTO() {}

    public ProductRequestDTO(String name, Long price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getter, Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getPrice() { return price; }
    public void setPrice(Long price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // Validation method
    public void validate() throws Exception {
        if (name.contains("카카오")) {
            if (!isApprovedByMD()) {
                throw new Exception("상품 이름에 '카카오'가 포함되어 있습니다. 담당 MD와 협의가 필요합니다.");
            }
        }
        if (name.length() > 15) {
            throw new Exception("상품 이름은 최대 15자까지 입력할 수 있습니다.");
        }
        if (!name.matches("^[a-zA-Z0-9가-힣()\\[\\]+\\-&/_]*$")) {
            throw new Exception("상품 이름에 허용되지 않는 특수 문자가 포함되어 있습니다.");
        }
    }

    private boolean isApprovedByMD() {
        return false; // 임시로 false 반환
    }
}
