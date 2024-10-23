package com.nhom3_221404.usecase.ViewInvoiceList;

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
            ViewInvoiceOutputDTO viewInvoiceOutputDTO = ViewInvoiceOutputDTO.builder()
                .id(invoice.getId())
                .roomId(invoice.getRoomId())
                .price(invoice.getPrice())
                .customerName(invoice.getCustomerName())
                .billedDate(invoice.getBilledDate())
                .build();

                outputDTOList.add(viewInvoiceOutputDTO);
            });
            viewILOutputB.present(outputDTOList);
    }
}
