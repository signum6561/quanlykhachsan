package com.nhom3_221404.dto;

import java.time.Month;

public class AverageInvoiceInputDTO {
    private Month month;

    public AverageInvoiceInputDTO(Month month) {
        this.month = month;
    }

    public Month getMonth() {
        return month;
    }
}