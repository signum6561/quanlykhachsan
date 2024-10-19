package com.nhom3_221404.usecase.ViewInvoiceList;

import java.util.ArrayList;
import java.util.List;

import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ViewInvoiceListUseCase implements ViewInvoiceListInputBoundary {

    private ViewInvoiceListOutputBoundary viewILOutputBoundary;
    private ViewInvoiceListDatabaseBoundary viewILDatabaseBoundary;

    @Override
    public void execute() {
        List<Invoice> invoiceList = viewILDatabaseBoundary.getInvoiceList();
        List<ViewInvoiceOutputDTO> outputDTOList = new ArrayList<>();

        invoiceList.forEach(invoice -> {
            ViewInvoiceOutputDTO viewInvoiceOutputDTO = ViewInvoiceOutputDTO.builder()
                .id(invoice.getId())
                .roomId(invoice.getRoomId())
                .price(invoice.getPrice())
                .billedDate(invoice.getBilledDate())
                .total(invoice.getTotal())
                .build();

                outputDTOList.add(viewInvoiceOutputDTO);
            });
            viewILOutputBoundary.present(outputDTOList);
        }

}
