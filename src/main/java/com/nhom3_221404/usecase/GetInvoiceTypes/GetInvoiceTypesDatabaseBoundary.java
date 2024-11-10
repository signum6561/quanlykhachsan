package com.nhom3_221404.usecase.GetInvoiceTypes;

import java.util.List;

import com.nhom3_221404.entity.InvoiceType;

public interface GetInvoiceTypesDatabaseBoundary {
    List<InvoiceType> getInvoiceTypes();
}
