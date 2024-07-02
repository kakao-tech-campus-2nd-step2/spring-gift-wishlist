package gift.Service;

import gift.Exception.ErrorCode;
import gift.Exception.ProductNameException;
import gift.Model.Product;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidity {
    private InputValidity(){
    }

    public static void checkKakaoValidity(String name){
        if(name.contains("카카오")){
            throw new ProductNameException(ErrorCode.INVALID_NAME_KAKAO);
        }
    }


}
