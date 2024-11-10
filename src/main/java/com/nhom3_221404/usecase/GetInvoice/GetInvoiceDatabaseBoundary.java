package com.nhom3_221404.usecase.GetInvoice;

import com.nhom3_221404.entity.Invoice;

public interface GetInvoiceDatabaseBoundary {

    Invoice getInvoice(String id);

}
