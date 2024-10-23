package com.nhom3_221404.usecase.CreateInvoice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.CreateInvoiceInputDTO;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;

public abstract class CreateInvoiceUseCase implements CreateInvoiceInputBoundary {
    private CreateInvoiceOutputBoundary CreateIOutputB;
    private CreateInvoiceDatabaseBoundary CreateIDatabaseB;

    public CreateInvoiceUseCase(CreateInvoiceOutputBoundary createIOutputB,
            CreateInvoiceDatabaseBoundary createIDatabaseB) {
        CreateIOutputB = createIOutputB;
        CreateIDatabaseB = createIDatabaseB;
    }

    @Override
    public void execute(CreateInvoiceInputDTO createInvoiceInputDTO) {
        Invoice invoice = null;
        String roomId = createInvoiceInputDTO.getRoomId();
        Double price = createInvoiceInputDTO.getPrice();
        String customerName = createInvoiceInputDTO.getCustomerName();
        LocalDateTime billedDate = createInvoiceInputDTO.getBilledDate();
        if (createInvoiceInputDTO.getInvoiceType() == InvoiceType.Daily) {
            
        }

    }

}
