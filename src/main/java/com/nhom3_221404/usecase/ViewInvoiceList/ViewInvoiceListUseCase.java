package com.nhom3_221404.usecase.ViewInvoiceList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.ViewInvoiceListResponse;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.exceptions.InternalDataAccessException;

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
        if(invoiceList == null) {
            viewILOutputB.presentError(
                new InternalDataAccessException()
            );
            return;
        }
        List<ViewInvoiceOutputDTO> outputDTOList = new ArrayList<>();

        invoiceList.forEach(invoice -> {
            String id = invoice.getId();
            String customerName = invoice.getCustomerName();
            Double price = invoice.getPrice();
            String roomId = invoice.getRoomId();
            Double total = invoice.getTotal();
            InvoiceType invoiceType = invoice.getInvoiceType();
            LocalDateTime billedDate = invoice.getBilledDate();

            ViewInvoiceOutputDTO viewInvoiceOutputDTO = 
                new ViewInvoiceOutputDTO(id, invoiceType, roomId, price, customerName, billedDate, total);

            outputDTOList.add(viewInvoiceOutputDTO);
        });

        viewILOutputB.presentResult(new ViewInvoiceListResponse(outputDTOList));
    }
}
