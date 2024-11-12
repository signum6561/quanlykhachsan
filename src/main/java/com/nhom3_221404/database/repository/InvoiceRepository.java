package com.nhom3_221404.database.repository;

import java.util.List;

import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceType;

public interface InvoiceRepository {
    List<Invoice> findAll();

    List<Invoice> findByPattern(String pattern);

    List<InvoiceType> findAllTypes();

    Invoice findById(String id);

    Invoice insert(Invoice invoice);

    Invoice save(Invoice invoice);

    boolean isExists(String id);

    void delete(String id);

    void deleteAll();
}
