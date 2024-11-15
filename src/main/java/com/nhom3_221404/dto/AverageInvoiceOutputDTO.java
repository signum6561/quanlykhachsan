package com.nhom3_221404.dto;

public class AverageInvoiceOutputDTO {
    private final double averageAmount;

    public AverageInvoiceOutputDTO(double averageAmount) {
        this.averageAmount = averageAmount;
    }

    public double getAverageAmount() {
        return averageAmount;
    }
}