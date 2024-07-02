package gift.controller.exception;

public class ProductException extends RuntimeException{
    private ProductErrorCode productErrorCode;
    private String detailMessage;

    public ProductException(ProductErrorCode productErrorCode){
        super(productErrorCode.getMessage());
        this.productErrorCode = productErrorCode;
        this.detailMessage = productErrorCode.getMessage();
    }

    public ProductException(ProductErrorCode productErrorCode, String detailMessage){
        super(detailMessage);
        this.productErrorCode = productErrorCode;
        this.detailMessage = detailMessage;
    }

}
