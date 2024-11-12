package com.nhom3_221404.database.repository;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.mybatis.guice.transactional.Isolation;
import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;
import com.nhom3_221404.database.dao.InvoiceDAO;
import com.nhom3_221404.database.dao.InvoiceDailyDAO;
import com.nhom3_221404.database.dao.InvoiceHourlyDAO;
import com.nhom3_221404.database.dao.InvoiceTypeDAO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.entity.InvoiceType;

public class InvoiceRepositoryImpl implements InvoiceRepository {

    @Inject
    InvoiceDAO invoiceDAO;

    @Inject
    InvoiceHourlyDAO invoiceHourlyDAO;

    @Inject
    InvoiceDailyDAO invoiceDailyDAO;

    @Inject
    InvoiceTypeDAO invoiceTypeDAO;

    public List<Invoice> findAll() {
        return invoiceDAO.selectAll();
    }

    public List<Invoice> findByPattern(String pattern) {
        return invoiceDAO.selectLikes(pattern);
    }

    public Invoice findById(String id) {
        return invoiceDAO.selectById(id);
    }

    @Transactional(
        executorType = ExecutorType.BATCH,
        isolation = Isolation.READ_UNCOMMITTED
    )
    public Invoice insert(InvoiceDaily invoice) {
        invoiceDAO.insert(invoice);
        invoiceDailyDAO.insert(invoice);
        return invoiceDAO.selectById(invoice.getId());
    }

    @Transactional(
        executorType = ExecutorType.BATCH,
        isolation = Isolation.READ_UNCOMMITTED
    )
    public Invoice insert(InvoiceHourly invoice) {
        invoiceDAO.insert(invoice);
        invoiceHourlyDAO.insert(invoice);
        return invoiceDAO.selectById(invoice.getId());
    }

    @Transactional(
        executorType = ExecutorType.BATCH,
        isolation = Isolation.READ_UNCOMMITTED
    )
    public Invoice save(InvoiceDaily invoice) {
        String id = invoice.getId();
        invoiceDAO.delete(id);
        invoiceDailyDAO.insert(invoice);
        return invoiceDAO.selectById(id);
    }

    @Transactional(
        executorType = ExecutorType.BATCH,
        isolation = Isolation.READ_UNCOMMITTED
    )
    public Invoice save(InvoiceHourly invoice) {
        String id = invoice.getId();
        invoiceDAO.delete(id);
        invoiceHourlyDAO.insert(invoice);
        return invoiceDAO.selectById(id);
    }

    @Transactional
    public void delete(String id) {
        invoiceDAO.delete(id);
    }

    @Transactional
    public boolean isExists(String id) {
        return invoiceDAO.selectById(id) != null;
    }

    @Transactional
    public void deleteAll() {
        invoiceDAO.deleteAll();
    }

    public Invoice insert(Invoice invoice) {
        if(invoice instanceof InvoiceDaily) {        
            return insert((InvoiceDaily) invoice);
        } else if(invoice instanceof InvoiceHourly) {        
            return insert((InvoiceHourly) invoice);
        }
        return null;
    }

    public Invoice save(Invoice invoice) {
        if(invoice instanceof InvoiceDaily) {        
            return insert((InvoiceDaily) invoice);
        } else if(invoice instanceof InvoiceHourly) {        
            return insert((InvoiceHourly) invoice);
        }
        return null;
    }

    @Transactional
    public List<InvoiceType> findAllTypes() {
        return invoiceTypeDAO.selectAll();
    }
}
