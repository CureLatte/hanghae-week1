package io.hhplus.tdd.point.domain.vo;

public class BusinessError extends RuntimeException {

    String message;

    public BusinessError(String message){
        this.message = message;
    }
}
