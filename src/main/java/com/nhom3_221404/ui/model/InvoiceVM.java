package com.nhom3_221404.ui.model;

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
public class InvoiceVM {
    private String id;

    private String roomId;

    private String invoiceType;

    private String price;

    private String customerName;

    private String billedDate;

    private String total;

}
