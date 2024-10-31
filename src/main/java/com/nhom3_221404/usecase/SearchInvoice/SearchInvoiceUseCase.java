package com.nhom3_221404.usecase.SearchInvoice;

import com.nhom3_221404.dto.ViewInvoiceInputDTO;
import com.nhom3_221404.dto.ViewInvoiceDailyDTO;
import com.nhom3_221404.entity.Invoice;

public class SearchInvoiceUseCase {
    private SearchInvoiceDatabaseBoundary searchInvoiceDatabaseBoundary;
    private SearchInvoiceOutputBoundary searchInvoiceOutputBoundary;

    public SearchInvoiceUseCase(SearchInvoiceDatabaseBoundary searchInvoiceDatabaseBoundary,
            SearchInvoiceOutputBoundary searchInvoiceOutputBoundary) {
        this.searchInvoiceDatabaseBoundary = searchInvoiceDatabaseBoundary;
        this.searchInvoiceOutputBoundary = searchInvoiceOutputBoundary;
    }

    public void execute(ViewInvoiceInputDTO searchInvoiceInputDTO) {

        String searchInvoice = searchInvoiceInputDTO.getId();

        Invoice invoice = searchInvoiceDatabaseBoundary.searchInvoice(searchInvoice);

        ViewInvoiceDailyDTO sOutputDTO = new ViewInvoiceDailyDTO(
                invoice.getId(),
                invoice.getRoomId(),
                invoice.getPrice(),
                invoice.getCustomerName(),
                invoice.getBilledDate(),
                invoice.getTotal());

        searchInvoiceOutputBoundary.presentData(sOutputDTO);
    }
}
