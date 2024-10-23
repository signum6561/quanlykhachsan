package com.nhom3_221404.database.repository;

import java.util.List;

import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;

public interface InvoiceRepository {
    List<Invoice> findAll();

    List<InvoiceHourly> findAllHourly();

    List<InvoiceDaily> findAllDaily();

    Invoice findById(String id);

    Invoice insert(Invoice invoice);

    Invoice insert(InvoiceDaily invoice);

    Invoice insert(InvoiceHourly invoice);

    Invoice save(Invoice invoice);
    
    Invoice save(InvoiceDaily invoice);
    
    Invoice save(InvoiceHourly invoice);

    boolean isExists(String id);

    void delete(String id);

    void deleteAll();
}
