package com.nhom3_221404.database.repository;

import java.util.List;

import com.google.inject.Inject;
import com.nhom3_221404.database.dao.InvoiceDAO;
import com.nhom3_221404.database.dao.InvoiceTypeDAO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceType;
import com.nhom3_221404.util.Page;
import com.nhom3_221404.util.Pageable;

public class InvoiceRepositoryImpl implements InvoiceRepository {

    @Inject
    InvoiceDAO invoiceDAO;

    @Inject
    InvoiceTypeDAO invoiceTypeDAO;

    @Inject
    InvoiceArchiver invoiceArchiver;

    public Page<Invoice> findAll(Pageable pageable) {
        List<Invoice> invoices = invoiceDAO.selectAll(pageable);
        int total = invoiceDAO.countAll();
        return new Page<>(invoices, total);
    }

    public Page<Invoice> findByPattern(String pattern, Pageable pageable) {
        List<Invoice> invoices = invoiceDAO.selectLikes(pattern, pageable);
        int total = invoiceDAO.countLikes(pattern);
        return new Page<>(invoices, total);
    }

    public Invoice findById(String id) {
        return invoiceDAO.selectById(id);
    }

    public void delete(Invoice invoice) {
        invoiceDAO.delete(invoice);
    }

    public void deleteAll() {
        invoiceDAO.deleteAll();
    }

    public void insert(Invoice invoice) {
        invoiceDAO.insert(invoice);
        invoice.acceptInsert(invoiceArchiver);
    }

    public void save(Invoice invoice) {
        invoiceDAO.update(invoice);
        boolean isTypeUpdate = !invoiceDAO.checkExistsByType(invoice);
        if(isTypeUpdate) {
            invoiceDAO.deleteTypeRecord(invoice);
            invoice.acceptInsert(invoiceArchiver);
        }
        else {
            invoice.acceptUpdate(invoiceArchiver);
        }
    }

    public List<InvoiceType> findAllTypes() {
        return invoiceTypeDAO.selectAll();
    }
}
