package com.dev.challenge.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalculationDto {
    private double result;

    public CalculationDto(double result) {
        this.result = result;
    }

}
