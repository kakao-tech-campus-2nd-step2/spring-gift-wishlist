package gift.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidity {
    private InputValidity(){
    }
    //이름
    public boolean nameValidity(String name){
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
    private boolean checkLengthValidity(String name){
        return name.length() > 15;
    }

    private boolean checkSpecialSymbolValidity(String name){
//        String regex = "[()[]+-&/_]";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(name);
        return true;
    }
    // 카카오를 포함하고 있으면 T, 아니면 F
    private boolean checkKakaoValidity(String name){
        return name.contains("카카오");
    }

    public boolean priceValidity(int price){
        return price > 0;
    }

}
