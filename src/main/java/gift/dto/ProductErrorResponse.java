package gift.dto;

import gift.exception.ProductErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductErrorResponse {
    private ProductErrorCode errorCode;
    private String errorMessage;
}
