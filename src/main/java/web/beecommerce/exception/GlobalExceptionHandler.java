package web.beecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Bắt lỗi validate
//    @ExceptionHandler({MethodArgumentNotValidException.class, ResponseStatusException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleException(Exception e, WebRequest request) {
//        log.info("đã vào handler exception");
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setTimestamp(new Date());
//        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
//        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
//
//        // cấu hình message
//        String message = e.getMessage();
//        int start = message.lastIndexOf("[");
//        int end = message.lastIndexOf("]");
//        message = message.substring(start + 1, end - 1);
//        errorResponse.setMessage(message);
//        return errorResponse;
//    }
}
