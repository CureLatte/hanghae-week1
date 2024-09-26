package io.hhplus.tdd.point.domain.vo;

import lombok.Getter;

@Getter
public class BusinessError extends RuntimeException {

    String message;

    public BusinessError(String message){
        this.message = message;
    }
}
