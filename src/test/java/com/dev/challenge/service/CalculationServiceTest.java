package com.dev.challenge.service;

import com.dev.challenge.services.CalculationServiceImpl;
import com.dev.challenge.services.PercentageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculationServiceTest {

    @Mock
    private PercentageService percentageService;

    @InjectMocks
    private CalculationServiceImpl calculationService;

    @Test
    void shouldCalculateWithPercentageCorrectly() {
        double num1 = 100;
        double num2 = 50;
        when(percentageService.getPercentage()).thenReturn(10.0);

        double result = calculationService.calculateWithPercentage(num1, num2);

        // sum = 150; 10% of 150 = 15 → total = 165
        assertEquals(165.0, result);
        verify(percentageService).getPercentage();
    }
}
