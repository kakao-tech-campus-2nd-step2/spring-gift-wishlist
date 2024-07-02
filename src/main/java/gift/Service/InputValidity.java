package gift.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidity {
    private InputValidity(){
    }

    // 이름이 15글자 넘으면 T 아니면 F
    public static boolean checkLengthValidity(String name){
        return name.length() > 15;
    }

    public static boolean checkSpecialSymbolValidity(String name){
        String replaceName = name.replaceAll("[()\\[\\]+&/_]","");//허용되는 특수문자 제거 해 replaceName에 저장
        String checkRegex = "[^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\s]";// 숫자, 영어, 한글, 공백 정규식
        // replaceName에 특수문자가 있다면 허용되는 특수문자가 아님
        Pattern pattern = Pattern.compile(checkRegex);
        Matcher matcher = pattern.matcher(replaceName);
        return matcher.find();
    }
    // 카카오를 포함하고 있으면 T, 아니면 F
    public static boolean checkKakaoValidity(String name){
        return name.contains("카카오");
    }

    public static boolean checkPriceValidity(int price){
        return price <= 0;
    }

}
