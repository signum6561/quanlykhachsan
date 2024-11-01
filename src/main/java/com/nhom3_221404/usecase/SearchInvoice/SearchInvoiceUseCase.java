package com.nhom3_221404.usecase.SearchInvoice;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.SearchInvoiceResponse;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;

public class SearchInvoiceUseCase implements SearchInvoiceInputBoundary {
    private SearchInvoiceDatabaseBoundary searchInvoiceDatabaseB;
    private SearchInvoiceOutputBoundary searchInvoiceOutputB;

    public SearchInvoiceUseCase(SearchInvoiceDatabaseBoundary searchInvoiceDatabaseB, SearchInvoiceOutputBoundary searchInvoiceOutputB) {
        this.searchInvoiceDatabaseB = searchInvoiceDatabaseB;
        this.searchInvoiceOutputB = searchInvoiceOutputB;
    }

    @Override
    public void execute(String keywords) {
        List<Invoice> searchResult = searchInvoiceDatabaseB.searchInvoice(keywords);
        List<ViewInvoiceOutputDTO> outputDTOList = new ArrayList<>();
        
        if(searchResult != null) {
            searchResult.forEach(invoice -> {
                String id = invoice.getId();
                String customerName = invoice.getCustomerName();
                Double price = invoice.getPrice();
                String roomId = invoice.getRoomId();
                Double total = invoice.getTotal();
                InvoiceType invoiceType = invoice.getInvoiceType();
                LocalDate billedDate = invoice.getBilledDate();
    
                ViewInvoiceOutputDTO viewInvoiceOutputDTO = 
                    new ViewInvoiceOutputDTO(id, invoiceType, roomId, price, customerName, billedDate, total);
    
                outputDTOList.add(viewInvoiceOutputDTO);
            });
        }

        searchInvoiceOutputB.present(new SearchInvoiceResponse(outputDTOList));
    }

}
