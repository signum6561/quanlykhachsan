package com.nhom3_221404.usecase.SearchInvoice;

import java.util.List;

import com.nhom3_221404.entity.Invoice;

public interface SearchInvoiceDatabaseBoundary {
    List<Invoice> searchInvoice(String pattern);    
}
