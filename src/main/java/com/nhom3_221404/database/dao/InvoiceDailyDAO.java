package com.nhom3_221404.database.dao;

import java.util.List;

import com.nhom3_221404.entity.InvoiceDaily;

public interface InvoiceDailyDAO {
    List<InvoiceDaily> selectAll();

    void insert(InvoiceDaily invoice);
}
