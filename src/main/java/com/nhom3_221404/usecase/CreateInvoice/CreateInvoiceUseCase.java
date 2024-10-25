package com.nhom3_221404.usecase.CreateInvoice;

import java.time.LocalDateTime;

import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.CreateInvoiceInputDTO;
import com.nhom3_221404.dto.CreateInvoiceOutDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;

public class CreateInvoiceUseCase implements CreateInvoiceInputBoundary {
    private CreateInvoiceOutputBoundary createIOutputB;
    private CreateInvoiceDatabaseBoundary createIDatabaseB;

    public CreateInvoiceUseCase(CreateInvoiceOutputBoundary createIOutputB,
            CreateInvoiceDatabaseBoundary createIDatabaseB) {
        this.createIOutputB = createIOutputB;
        this.createIDatabaseB = createIDatabaseB;
    }

    @Override
    public void execute(CreateInvoiceInputDTO createInvoiceInputDTO) {
        Invoice invoice = null;

        InvoiceType invoiceType = createInvoiceInputDTO.getInvoiceType();
        String roomId = createInvoiceInputDTO.getRoomId();
        Double price = createInvoiceInputDTO.getPrice();
        String customerName = createInvoiceInputDTO.getCustomerName();
        LocalDateTime billedDate = createInvoiceInputDTO.getBilledDate();
        if (invoiceType == InvoiceType.Daily) {
            invoice = new InvoiceDaily();
            ((InvoiceDaily) invoice).setRentalDays(createInvoiceInputDTO.getRentalDays());
        } else if (invoiceType == InvoiceType.Hourly) {
            invoice = new InvoiceHourly();
            ((InvoiceHourly) invoice).setRentalHours(createInvoiceInputDTO.getRentalHours());
        } else {
            createIOutputB.presentError(new RuntimeException("Loại hóa đơn không hợp lệ"));
            return;
        }
        invoice.setRoomId(roomId);
        invoice.setInvoiceType(invoiceType);
        invoice.setPrice(price);
        invoice.setCustomerName(customerName);
        invoice.setBilledDate(billedDate);
        invoice.getTotal();

        String InvoiceID = createIDatabaseB.createInvoiceID(invoice);

        Invoice newInvoice = createIDatabaseB.findInvoiceID(InvoiceID);

        CreateInvoiceOutDTO createInvoiceOutDTO = new CreateInvoiceOutDTO(newInvoice.getId(),
                newInvoice.getRoomId(),
                newInvoice.getPrice(), newInvoice.getCustomerName(), newInvoice.getBilledDate(),
                newInvoice.getTotal());

        createIOutputB.presentData(createInvoiceOutDTO);

    }

}
