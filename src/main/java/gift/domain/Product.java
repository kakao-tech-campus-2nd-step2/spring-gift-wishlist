package gift.domain;

import gift.exception.NameException;
import java.util.regex.Pattern;

public class Product {

    private static final Pattern ALLOWED_SPECIAL_CHARACTERS = Pattern.compile("[\\w\\s\\(\\)\\[\\]\\+\\-\\&\\/가-힣]*");
    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;

    public Product(){
    }
    public Product(Long id, String name, Integer price, String imageUrl){
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id){this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public Integer getPrice() { return this.price;}

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void validate() throws NameException {
        findNameLengthException();
        findAllowedCharactersException();
        findSpecificCharacterException();
    }

    private void findNameLengthException() throws NameException{
        if (this.name.length() > 15){
            throw new NameException("최대 15자리까지 입력하실 수 있습니다.");
        }
    }
    private void findAllowedCharactersException() throws NameException{
        if (!ALLOWED_SPECIAL_CHARACTERS.matcher(this.name).matches()) {
            throw new NameException("특수 문자는 '(), [], +, -, &, /, _ '만 사용가능 합니다.");
        }
    }

    private void findSpecificCharacterException() throws NameException{
        if (this.name.contains("카카오")){
            throw new NameException("담당 MD와 협의해 주세요.");
        }
    }

}
