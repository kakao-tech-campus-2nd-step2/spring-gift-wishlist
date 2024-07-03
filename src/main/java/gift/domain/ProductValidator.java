package gift.domain;

public class ProductValidator {

    public boolean isValidId(Product product){
        return product.getId() !=null;
    }

    public boolean isValidUpdate(Product product, long id){
        return product.getId()==id;
    }

}
