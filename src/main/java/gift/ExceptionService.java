package gift;

import gift.ProductDto;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {
    private static final Pattern ALLOWED_SPECIAL_CHARACTERS = Pattern.compile("[\\w\\s\\(\\)\\[\\]\\+\\-\\&\\/가-힣]*");
    public void findNameException(String name) throws NameException{
        if (name.length() > 15){
            throw new NameException("최대 15자리까지 입력하실 수 있습니다.");
        }
        if (!ALLOWED_SPECIAL_CHARACTERS.matcher(name).matches()) {
            throw new NameException("특수 문자는 '(), [], +, -, &, /, _ '만 사용가능 합니다.");
        }
        if (name.contains("카카오")){
            throw new NameException("담당 MD와 협의해 주세요.");
        }
    }
}
