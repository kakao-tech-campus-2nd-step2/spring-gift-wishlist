package gift.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SaveProductDTO {
    int id;

    @NotBlank(message = "이름 공백 안됨")
    @Size(max = 15, message = "15글자까지만 가능")
    @Pattern(regexp = "^[a-zA-Z0-9()\\[\\]+\\-&/_]+$", message = "특수기호 안됨")

    String name;
    int price;
    String imageUrl;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public SaveProductDTO(int id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
