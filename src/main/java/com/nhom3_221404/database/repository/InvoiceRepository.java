package com.nhom3_221404.database.repository;

import java.util.List;

import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceType;
import com.nhom3_221404.util.Page;
import com.nhom3_221404.util.Pageable;

public interface InvoiceRepository {
    Page<Invoice> findAll(Pageable pageable);

    Page<Invoice> findByPattern(String pattern, Pageable pageable);

    List<InvoiceType> findAllTypes();

    Invoice findById(String id);

    void insert(Invoice invoice);

    void save(Invoice invoice);

    void delete(Invoice invoice);

    void deleteAll();
}
