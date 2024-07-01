package gift.Service;

public class InputValidity {
    private InputValidity(){
    }
    public boolean priceValidity(int price){
        return price > 0;
    }

}
