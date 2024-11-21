package com.nhom3_221404.database.repository;

import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;

public interface InvoiceArchiver {
    void insert(InvoiceDaily invoice);

    void update(InvoiceDaily invoice);

    void insert(InvoiceHourly invoice);

    void update(InvoiceHourly invoice);
}
