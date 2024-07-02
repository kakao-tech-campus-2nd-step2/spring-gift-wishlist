package gift.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidity {
    private InputValidity(){
    }
    //이름
    public static boolean nameValidity(String name){
        if(checkLengthValidity(name)){
            return false;
        }
        if(checkSpecialSymbolValidity(name)){
            return false;
        }
        if(checkKakaoValidity(name)){
            return false;
        }
        return true;
    }
    // 이름이 15글자 넘으면 T 아니면 F
    public static boolean checkLengthValidity(String name){
        return name.length() > 15;
    }

    public static boolean checkSpecialSymbolValidity(String name){
        String correctRegex = "[()[]+-&/_]";
        String replaceStr = name.replace(correctRegex,"");
        String checkRegex = "[^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]";
        Pattern pattern = Pattern.compile(checkRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
    // 카카오를 포함하고 있으면 T, 아니면 F
    public static boolean checkKakaoValidity(String name){
        return name.contains("카카오");
    }

    public static boolean priceValidity(int price){
        return price > 0;
    }

}
