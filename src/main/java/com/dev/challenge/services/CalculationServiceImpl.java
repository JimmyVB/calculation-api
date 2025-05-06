package com.dev.challenge.services;

import com.dev.challenge.exceptions.CalculationException;
import com.dev.challenge.exceptions.PercentageServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {

    private final PercentageService percentageService;

    @Autowired
    public CalculationServiceImpl(PercentageService percentageService) {
        this.percentageService = percentageService;
    }

    @Override
    public double calculateWithPercentage(double num1, double num2) {
        try {
            double percentage = percentageService.getPercentage();
            double sum = num1 + num2;
            return sum + (sum * percentage / 100);
        } catch (PercentageServiceException e) {
            throw new CalculationException("Failed to calculate with percentage: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new CalculationException("Unexpected error occurred during calculation", e);
        }
    }
}
