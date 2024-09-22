package io.hhplus.tdd.point.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchRequestDto {
    long amount;

    public PatchRequestDto(long amount) {
        this.amount = amount;
    }


}

