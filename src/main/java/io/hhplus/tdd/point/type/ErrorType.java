package io.hhplus.tdd.point.type;

import io.hhplus.tdd.point.record.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorType extends RuntimeException{
    ErrorResponse errorResponse;

    public ErrorType(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;

    }
}
