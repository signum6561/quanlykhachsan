package com.nhom3_221404.usecase.CreateInvoice;

import com.nhom3_221404.entity.Invoice;

public interface CreateInvoiceDatabaseBoundary {

    int createInvoiceID(Invoice invoice);
    
    Invoice findInvoiceID(int newInvoiceID);
    

}
