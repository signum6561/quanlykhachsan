package com.nhom3_221404.usecase.AverageInvoice;

import java.util.List;
import com.nhom3_221404.entity.Invoice;

public interface AverageInvoiceDatabaseBoundary {
    List<Invoice> getAllInvoices();
}