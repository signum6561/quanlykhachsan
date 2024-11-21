package com.nhom3_221404.database.repository;

import com.google.inject.Inject;
import com.nhom3_221404.database.dao.InvoiceDailyDAO;
import com.nhom3_221404.database.dao.InvoiceHourlyDAO;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;

public class InvoiceArchiverImpl implements InvoiceArchiver {

    @Inject
    InvoiceDailyDAO dailyDAO;

    @Inject
    InvoiceHourlyDAO hourlyDAO;

    @Override
    public void insert(InvoiceDaily invoice) {
        dailyDAO.insert(invoice);
    }

    @Override
    public void update(InvoiceDaily invoice) {
        dailyDAO.update(invoice);
    }

    @Override
    public void insert(InvoiceHourly invoice) {
        hourlyDAO.insert(invoice);
    }

    @Override
    public void update(InvoiceHourly invoice) {
        hourlyDAO.update(invoice);
    }

}
