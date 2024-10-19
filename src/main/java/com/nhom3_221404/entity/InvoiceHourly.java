package com.nhom3_221404.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class InvoiceHourly extends Invoice {

    private Integer rentalHours;

    public InvoiceHourly(String id, String roomId, Double price, String customerName, Date billedDate,
        Integer rentalHours) {
        super(id, roomId, price, customerName, billedDate);
        this.rentalHours = rentalHours;
    }

    @Override
    public Double getTotal() {
        if(rentalHours > 24 && rentalHours < 30) {
            return 24 * price;
        }
        return rentalHours * price;
    }

}
