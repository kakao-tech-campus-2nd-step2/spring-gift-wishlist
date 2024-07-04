package gift.model;

import java.util.HashMap;

public record WishListDTO(String email, HashMap<String, Integer> products) {

}
