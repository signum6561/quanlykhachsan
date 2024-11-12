package com.nhom3_221404.usecase.GetInvoiceList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.dto.GetInvoiceListResponse;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;

public class GetInvoiceListUseCase implements GetInvoiceListInputBoundary {

    private GetInvoiceListOutputBoundary viewILOutputB;
    private GetInvoiceListDatabaseBoundary viewILDatabaseB;

    public GetInvoiceListUseCase(GetInvoiceListOutputBoundary viewILOutputB,
            GetInvoiceListDatabaseBoundary viewILDatabaseB) {
        this.viewILOutputB = viewILOutputB;
        this.viewILDatabaseB = viewILDatabaseB;
    }

    @Override
    public void execute() {
        List<Invoice> invoiceList = viewILDatabaseB.getInvoiceList();
        if(invoiceList == null) {
            viewILOutputB.presentError(Errors.InternalDataAccess);
            return;
        }
        List<ViewInvoiceOutputDTO> outputDTOList = new ArrayList<>();

        invoiceList.forEach(invoice -> {
            String id = invoice.getId();
            String customerName = invoice.getCustomerName();
            Double price = invoice.getPrice();
            String roomId = invoice.getRoomId();
            Double total = invoice.getTotal();
            String invoiceType = invoice.getInvoiceType().getName();
            LocalDate billedDate = invoice.getBilledDate();

            ViewInvoiceOutputDTO viewInvoiceOutputDTO = 
                new ViewInvoiceOutputDTO(id, invoiceType, roomId, price, customerName, billedDate, total);

            outputDTOList.add(viewInvoiceOutputDTO);
        });

        viewILOutputB.presentResult(new GetInvoiceListResponse(outputDTOList));
    }
}
