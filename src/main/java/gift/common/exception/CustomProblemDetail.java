package gift.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.net.URI;
import java.util.List;
import org.springframework.http.ProblemDetail;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

public class CustomProblemDetail extends ProblemDetail {

    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ValidationError> invalidParams;

    public CustomProblemDetail(ErrorCode errorCode, WebRequest request) {
        super(errorCode.getHttpStatus().value());
        this.setType(URI.create("https://gift.com/docs/error#"+errorCode.getCode()));
        this.setTitle(errorCode.getTitle());
        this.setDetail(errorCode.getDetail());
        this.setCode(errorCode.getCode());
        this.setInstance(URI.create(((ServletWebRequest)request).getRequest().getRequestURI()));
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setInvalidParams(List<ValidationError> invalidParams) {
        this.invalidParams = invalidParams;
    }

    public List<ValidationError> getInvalidParams() {
        return invalidParams;
    }
}
