package io.hhplus.tdd;

import io.hhplus.tdd.point.domain.vo.BusinessError;
import io.hhplus.tdd.point.domain.vo.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
class ApiControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        String errMessage = e.getMessage();

        if(errMessage.isEmpty()) {
            errMessage = "에러가 발생했습니다";

        }
        return ResponseEntity.status(500).body(new ErrorResponse("500", errMessage));
    }

    @ExceptionHandler(value = BusinessError.class)
    public ResponseEntity<ErrorResponse> handleBusinessError(BusinessError businessError) {

        return ResponseEntity.status(500).body(new ErrorResponse("500", businessError.getMessage()));
    }
}
