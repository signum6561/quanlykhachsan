package com.nhom3_221404.database.dao;

import java.util.List;

import com.nhom3_221404.entity.InvoiceType;

public interface InvoiceTypeDAO {
    List<InvoiceType> selectAll();

    InvoiceType selectByCode(String code);
}
