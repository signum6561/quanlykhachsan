package com.nhom3_221404.usecase.GetInvoice;

import com.nhom3_221404.dto.GetInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;

public class GetInvoiceUseCase {
    private GetInvoiceDatabaseBoundary getInvoiceDatabaseBoundary;
    private GetInvoiceOutputBoundary getInvoiceOutputBoundary;

    public GetInvoiceUseCase(GetInvoiceDatabaseBoundary getInvoiceDatabaseBoundary,
            GetInvoiceOutputBoundary getInvoiceOutputBoundary) {
        this.getInvoiceDatabaseBoundary = getInvoiceDatabaseBoundary;
        this.getInvoiceOutputBoundary = getInvoiceOutputBoundary;
    }

    public void execute(String invoiceId) {

        Invoice invoice = getInvoiceDatabaseBoundary.getInvoice(invoiceId);

        GetInvoiceOutputDTO getInvoiceOutputDTO = new GetInvoiceOutputDTO(
                invoice.getId(),
                invoice.getInvoiceType().getName(),
                invoice.getRoomId(),
                invoice.getPrice(),
                invoice.getCustomerName(),
                invoice.getBilledDate(),
                invoice.getTotal(),
                (invoice instanceof InvoiceDaily) ? ((InvoiceDaily) invoice).getRentalDays() : null,
                (invoice instanceof InvoiceHourly) ? ((InvoiceHourly) invoice).getRentalHours() : null);

        getInvoiceOutputBoundary.presentData(getInvoiceOutputDTO);
    }
}
