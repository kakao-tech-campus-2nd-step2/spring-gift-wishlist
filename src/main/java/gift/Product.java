package gift;

public record Product (long id, String name, int price, String imageUrl){

    public Product(String name, int price, String imageUrl){
        this(-1,name,price,imageUrl);
    }
}
