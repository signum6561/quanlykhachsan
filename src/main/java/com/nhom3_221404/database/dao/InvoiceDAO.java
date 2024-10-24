package com.nhom3_221404.database.dao;

import java.util.List;

import com.nhom3_221404.entity.Invoice;

public interface InvoiceDAO {
    List<Invoice> selectAll();

    Invoice selectById(String id);

    void insert(Invoice invoice);
        
    void delete(String id);

    void deleteAll();
}
