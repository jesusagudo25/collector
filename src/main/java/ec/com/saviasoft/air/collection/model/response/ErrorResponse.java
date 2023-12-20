package ec.com.saviasoft.air.collection.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private int errorCode;
    private String message;
    private String status;

    public ErrorResponse(int errorCode, String message, String status) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }
}
