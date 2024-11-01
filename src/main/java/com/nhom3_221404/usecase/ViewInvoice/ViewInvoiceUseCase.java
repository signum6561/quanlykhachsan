package com.nhom3_221404.usecase.ViewInvoice;

import com.nhom3_221404.dto.ViewInvoiceInputDTO;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;

public class ViewInvoiceUseCase {
    private ViewInvoiceDatabaseBoundary viewInvoiceDatabaseBoundary;
    private ViewInvoiceOutputBoundary viewInvoiceOutputBoundary;

    public ViewInvoiceUseCase(ViewInvoiceDatabaseBoundary viewInvoiceDatabaseBoundary,
            ViewInvoiceOutputBoundary viewInvoiceOutputBoundary) {
        this.viewInvoiceDatabaseBoundary = viewInvoiceDatabaseBoundary;
        this.viewInvoiceOutputBoundary = viewInvoiceOutputBoundary;
    }

    public void execute(ViewInvoiceInputDTO viewInvoiceInputDTO) {

        String viewInvoice = viewInvoiceInputDTO.getId();

        Invoice invoice = viewInvoiceDatabaseBoundary.viewInvoice(viewInvoice);

        ViewInvoiceOutputDTO vInvoiceOutputDTO = new ViewInvoiceOutputDTO(
                invoice.getId(),
                invoice.getInvoiceType(),
                invoice.getRoomId(),
                invoice.getPrice(),
                invoice.getCustomerName(),
                invoice.getBilledDate(),
                invoice.getTotal());

        viewInvoiceOutputBoundary.presentData(vInvoiceOutputDTO);
    }
}
