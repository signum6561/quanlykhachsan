package com.nhom3_221404.usecase.ViewInvoice;

import com.nhom3_221404.dto.ViewInvoiceInputDTO;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;

public class ViewInvoiceUseCase {
    private ViewInvoiceDatabaseBoundary searchInvoiceDatabaseBoundary;
    private ViewInvoiceOutputBoundary searchInvoiceOutputBoundary;

    public ViewInvoiceUseCase(ViewInvoiceDatabaseBoundary searchInvoiceDatabaseBoundary,
            ViewInvoiceOutputBoundary searchInvoiceOutputBoundary) {
        this.searchInvoiceDatabaseBoundary = searchInvoiceDatabaseBoundary;
        this.searchInvoiceOutputBoundary = searchInvoiceOutputBoundary;
    }

    public void execute(ViewInvoiceInputDTO searchInvoiceInputDTO) {

        String searchInvoice = searchInvoiceInputDTO.getId();

        Invoice invoice = searchInvoiceDatabaseBoundary.viewInvoice(searchInvoice);

        ViewInvoiceOutputDTO sOutputDTO = new ViewInvoiceOutputDTO(
                invoice.getId(),
                invoice.getInvoiceType(),
                invoice.getRoomId(),
                invoice.getPrice(),
                invoice.getCustomerName(),
                invoice.getBilledDate(),
                invoice.getTotal());

        searchInvoiceOutputBoundary.presentData(sOutputDTO);
    }
}
