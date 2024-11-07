package com.nhom3_221404.usecase.GetInvoice;

import com.nhom3_221404.dto.GetInvoiceInputDTO;
import com.nhom3_221404.dto.GetInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;

public class GetInvoiceUseCase {
    private GetInvoiceDatabaseBoundary getInvoiceDatabaseBoundary;
    private GetInvoiceOutputBoundary getInvoiceOutputBoundary;

    public GetInvoiceUseCase(GetInvoiceDatabaseBoundary getInvoiceDatabaseBoundary,
            GetInvoiceOutputBoundary getInvoiceOutputBoundary) {
        this.getInvoiceDatabaseBoundary = getInvoiceDatabaseBoundary;
        this.getInvoiceOutputBoundary = getInvoiceOutputBoundary;
    }

    public void execute(GetInvoiceInputDTO getInvoiceInputDTO) {

        String getInvoice = getInvoiceInputDTO.getId();

        Invoice invoice = getInvoiceDatabaseBoundary.getInvoice(getInvoice);

        GetInvoiceOutputDTO getInvoiceOutputDTO = new GetInvoiceOutputDTO(
                invoice.getId(),
                invoice.getInvoiceType(),
                invoice.getRoomId(),
                invoice.getPrice(),
                invoice.getCustomerName(),
                invoice.getBilledDate(),
                invoice.getTotal());

        getInvoiceOutputBoundary.presentData(getInvoiceOutputDTO);
    }
}
