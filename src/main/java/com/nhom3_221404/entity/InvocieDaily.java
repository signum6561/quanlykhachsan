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
public class InvocieDaily extends Invoice {
    
    private Integer rentalDays;

    public InvocieDaily(String id, String roomId, Double price, String customerName, Date billedDate, Integer rentalDays) {
        super(id, roomId, price, customerName, billedDate);
        this.rentalDays = rentalDays;
    }

    @Override
    public Double getTotal() {
        if(rentalDays - 7 < 0) {
            return rentalDays * price;
        }
        return (1.4 + 0.8 * rentalDays) * price;
    }
}
