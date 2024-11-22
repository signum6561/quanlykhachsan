package com.nhom3_221404.usecase.GetInvoice;

import com.google.inject.Inject;
import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;

public class GetInvoiceUseCase implements GetInvoiceInputBoundary {
    private final GetInvoiceDatabaseBoundary getInvoiceDatabaseBoundary;
    private final GetInvoiceOutputBoundary getInvoiceOutputBoundary;

    @Inject
    public GetInvoiceUseCase(GetInvoiceDatabaseBoundary getInvoiceDatabaseBoundary,
            GetInvoiceOutputBoundary getInvoiceOutputBoundary) {
        this.getInvoiceDatabaseBoundary = getInvoiceDatabaseBoundary;
        this.getInvoiceOutputBoundary = getInvoiceOutputBoundary;
    }

    public void execute(String invoiceId) {
        Invoice invoice = getInvoiceDatabaseBoundary.getInvoice(invoiceId);

        InvoiceOutputDTO getInvoiceOutputDTO = new InvoiceOutputDTO();
        getInvoiceOutputDTO.setId(invoice.getId());
        getInvoiceOutputDTO.setCustomerName(invoice.getCustomerName());
        getInvoiceOutputDTO.setBilledDate(invoice.getBilledDate());
        getInvoiceOutputDTO.setInvoiceType(invoice.getInvoiceType().getName());
        getInvoiceOutputDTO.setRoomId(invoice.getRoomId());
        getInvoiceOutputDTO.setPrice(invoice.getPrice());
        getInvoiceOutputDTO.setTotal(invoice.getTotal());
        
        switch (invoice.getType()) {
            case DAILY:
                InvoiceDaily invoiceDaily = (InvoiceDaily) invoice;
                getInvoiceOutputDTO.setRentalDays(invoiceDaily.getRentalDays());
                break;
            case HOURLY:
                InvoiceHourly invoiceHourly = (InvoiceHourly) invoice;
                getInvoiceOutputDTO.setRentalDays(invoiceHourly.getRentalHours());
                break;
        }

        getInvoiceOutputBoundary.present(getInvoiceOutputDTO);
    }
}
