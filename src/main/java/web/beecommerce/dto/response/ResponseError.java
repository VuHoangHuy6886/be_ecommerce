package web.beecommerce.dto.response;

import lombok.Getter;

@Getter
public class ResponseError {
    private Integer code;
    private String message;

    public ResponseError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
