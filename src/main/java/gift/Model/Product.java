package gift.Model;

public record Product(long id, String name, int price, String imageUrl){
    @Override
    public String toString() {
        return "id : " + id + ", name : " + name + ", price : " + price + ", imageUrl : " + imageUrl;
    }
}