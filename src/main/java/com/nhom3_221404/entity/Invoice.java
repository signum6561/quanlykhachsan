package com.nhom3_221404.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class Invoice {
    
    protected String id;

    protected String roomId;

    protected Double price;
    
    protected String customerName;

    protected Date billedDate;

    public abstract Double getTotal();
}
