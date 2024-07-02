package gift.model;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private String imgUrl;
    private Boolean isDeleted;

    public Product(Long id, String name, Integer price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.isDeleted = Boolean.FALSE;
    }

    public Product(Long id, String name, Integer price, String imgUrl, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setId(Long id) {
        if(this.id == null){
            this.id = id;
        }
    }
    public void delete(){
        this.isDeleted = Boolean.TRUE;
    }

    public boolean isNew(){
        return this.id == null;
    }
}
