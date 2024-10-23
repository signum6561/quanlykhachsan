package com.nhom3_221404.usecase.ViewInvoiceList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;

public class ViewInvoiceListUseCase implements ViewInvoiceListInputBoundary {

    private ViewInvoiceListOutputBoundary viewILOutputB;
    private ViewInvoiceListDatabaseBoundary viewILDatabaseB;

    public ViewInvoiceListUseCase(ViewInvoiceListOutputBoundary viewILOutputB,
            ViewInvoiceListDatabaseBoundary viewILDatabaseB) {
        this.viewILOutputB = viewILOutputB;
        this.viewILDatabaseB = viewILDatabaseB;
    }

    @Override
    public void execute() {
        List<Invoice> invoiceList = viewILDatabaseB.getInvoiceList();
        List<ViewInvoiceOutputDTO> outputDTOList = new ArrayList<>();

        invoiceList.forEach(invoice -> {
            String id = invoice.getId();
            String customerName = invoice.getCustomerName();
            Double price = invoice.getPrice();
            String roomId = invoice.getRoomId();
            Double total = invoice.getTotal();
            LocalDateTime billedDate = invoice.getBilledDate();

            ViewInvoiceOutputDTO viewInvoiceOutputDTO = 
                new ViewInvoiceOutputDTO(id, roomId, price, customerName, billedDate, total);

            outputDTOList.add(viewInvoiceOutputDTO);
        });

        viewILOutputB.present(outputDTOList);
    }
}
