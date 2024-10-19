package com.nhom3_221404.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewInvoiceOutputDTO {
    protected String id;
    protected String roomId;
    protected Double price;
    protected String customerName;
    protected Date billedDate;
    protected Double total;
}
