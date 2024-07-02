package gift.Service;

import gift.Exception.ErrorCode;
import gift.Exception.ProductNameException;
import gift.Model.Product;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidity {
    private InputValidity(){
    }

    public static void checkNameAndPriceValidity(Product product){
        if(InputValidity.checkLengthValidity(product.getName())){
            throw new ProductNameException(ErrorCode.INVALID_NAME_LENGTH);
        }
        if(InputValidity.checkSpecialSymbolValidity(product.getName())){
            throw new ProductNameException(ErrorCode.INVALID_NAME_SPECIAL_SYMBOL);
        }
        if(InputValidity.checkKakaoValidity(product.getName())){
            throw new ProductNameException(ErrorCode.INVALID_NAME_KAKAO);
        }
        if(InputValidity.checkPriceValidity(product.getPrice())){
            throw new ProductNameException(ErrorCode.INVALID_PRICE);
        }
    }

    // 이름이 15글자 넘으면 T 아니면 F
    private static boolean checkLengthValidity(String name){
        return name.length() > 15;
    }

    private static boolean checkSpecialSymbolValidity(String name){
        String replaceName = name.replaceAll("[()\\[\\]+&/_]","");//허용되는 특수문자 제거 해 replaceName에 저장
        String checkRegex = "[^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\s]";// 숫자, 영어, 한글, 공백 정규식
        // replaceName에 특수문자가 있다면 허용되는 특수문자가 아님
        Pattern pattern = Pattern.compile(checkRegex);
        Matcher matcher = pattern.matcher(replaceName);
        return matcher.find();
    }
    // 카카오를 포함하고 있으면 T, 아니면 F
    private static boolean checkKakaoValidity(String name){
        return name.contains("카카오");
    }

    private static boolean checkPriceValidity(int price){
        return price <= 0;
    }

}
