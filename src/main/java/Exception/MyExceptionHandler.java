package Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class MyExceptionHandler {
    public static String back(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception404.class)
    public void exception(Exception404 e) {
        back(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception500.class)
    public void exception(Exception500 e) {
        back(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(Exception401.class)
    public void exception(Exception401 e) {
        back(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception400.class)
    public void exception(Exception400 e) {
        back(e.getMessage());
    }
}
