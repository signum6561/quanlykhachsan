package com.nhom3_221404.database.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;

import com.nhom3_221404.database.dao.InvoiceDAO;
import com.nhom3_221404.database.dao.InvoiceDailyDAO;
import com.nhom3_221404.database.dao.InvoiceHourlyDAO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;

public class InvoiceRepositoryImpl implements InvoiceRepository {
    InvoiceDAO invoiceDAO;
    InvoiceHourlyDAO invoiceHourlyDAO;
    InvoiceDailyDAO invoiceDailyDAO;
    SqlSessionManager sessionManager;

    public InvoiceRepositoryImpl(SqlSessionFactory sessionFactory) {
        this(SqlSessionManager.newInstance(sessionFactory));
    }

    public InvoiceRepositoryImpl(SqlSessionManager sessionManager) {
        this.sessionManager = sessionManager;
        invoiceDAO = sessionManager.getMapper(InvoiceDAO.class);
        invoiceDailyDAO = sessionManager.getMapper(InvoiceDailyDAO.class);
        invoiceHourlyDAO = sessionManager.getMapper(InvoiceHourlyDAO.class);
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceDAO.selectAll(); 
    }

    @Override
    public List<InvoiceHourly> findAllHourly() {
        return invoiceHourlyDAO.selectAll();
    }

    @Override
    public List<InvoiceDaily> findAllDaily() {
        return invoiceDailyDAO.selectAll(); 
    }

    @Override
    public Invoice findById(String id) {
        return invoiceDAO.selectById(id); 
    }

    @Override
    public Invoice insert(InvoiceDaily invoice) {
        sessionManager.startManagedSession();
        try {
            invoiceDAO.insert(invoice);
            invoiceDailyDAO.insert(invoice);
            Invoice result = invoiceDAO.selectById(invoice.getId());
            sessionManager.commit();
            return result;    
        } catch(Throwable t) {
            sessionManager.rollback();
            throw t;
        } finally {
            sessionManager.close();
        }
    }
    @Override
    public Invoice insert(InvoiceHourly invoice) {
        sessionManager.startManagedSession();
        try {
            invoiceDAO.insert(invoice);
            invoiceHourlyDAO.insert(invoice);
            Invoice result = invoiceDAO.selectById(invoice.getId());
            sessionManager.commit();
            return result;    
        } catch(Throwable t) {
            sessionManager.rollback();
            throw t;
        } finally {
            sessionManager.close();
        }
    }

    @Override
    public Invoice save(InvoiceDaily invoice) {
        try {
            String id = invoice.getId();
            invoiceDAO.delete(id);
            invoiceDailyDAO.insert(invoice);
            Invoice result = findById(invoice.getId());
            sessionManager.commit();
            return result;
        } catch(Throwable t) {
            sessionManager.rollback();
            throw t;
        } finally {
            sessionManager.close();
        }
    }

    @Override
    public Invoice save(InvoiceHourly invoice) {
        sessionManager.startManagedSession();
        try {
            String id = invoice.getId();
            invoiceDAO.delete(id);
            invoiceHourlyDAO.insert(invoice);
            Invoice result = invoiceDAO.selectById(id);
            sessionManager.commit();
            return result;
        } catch(Throwable t) {
            sessionManager.rollback();
            throw t;
        } finally {
            sessionManager.close();
        }
    }

    @Override
    public void delete(String id) {
        sessionManager.startManagedSession();
        try {
            invoiceDAO.delete(id);
            sessionManager.commit();
        } catch(Throwable t) {
            sessionManager.rollback();
            throw t;
        } finally {
            sessionManager.close();
        }
    }

    @Override
    public boolean isExists(String id) {
        return invoiceDAO.selectById(id) != null;
    }

    @Override
    public void deleteAll() {
        sessionManager.startManagedSession();
        try {
            invoiceDAO.deleteAll();
            sessionManager.commit();
        } catch(Throwable t) {
            sessionManager.rollback();
            throw t;
        } finally {
            sessionManager.close();
        }
    }

    @Override
    public Invoice insert(Invoice invoice) {
        switch (invoice.getInvoiceType()) {
            case Daily:
                return insert((InvoiceDaily) invoice);
            case Hourly:
                return insert((InvoiceHourly) invoice);
        }
        return null;
    }

    @Override
    public Invoice save(Invoice invoice) {
        switch (invoice.getInvoiceType()) {
            case Daily:
                return insert((InvoiceDaily) invoice);
            case Hourly:
                return insert((InvoiceHourly) invoice);
        }
        return null;
    }
}
