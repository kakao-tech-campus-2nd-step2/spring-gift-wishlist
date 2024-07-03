package gift.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDTO>> handleValidationExceptions(
        MethodArgumentNotValidException exception) {
        List<ErrorDTO> fieldErrorDTOList = createErrorDTOList(exception);
        return new ResponseEntity<>(fieldErrorDTOList, HttpStatus.BAD_REQUEST);
    }

    private List<ErrorDTO> createErrorDTOList(MethodArgumentNotValidException exception) {
        List<ErrorDTO> fieldErrorDTOList = new ArrayList<>();

        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();

        for (ObjectError objectError : allErrors) {
            FieldError fieldError = (FieldError) objectError;
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            ErrorDTO errorDTO = new ErrorDTO(fieldName, errorMessage);
            fieldErrorDTOList.add(errorDTO);
        }

        return fieldErrorDTOList;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
