package com.nhom3_221404.database.dao;

import java.util.List;

import com.nhom3_221404.entity.InvoiceHourly;

public interface InvoiceHourlyDAO {
    List<InvoiceHourly> selectAll();

    void insert(InvoiceHourly invoice);
}
