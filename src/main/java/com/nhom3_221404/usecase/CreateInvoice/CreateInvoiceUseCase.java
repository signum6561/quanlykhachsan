package com.nhom3_221404.usecase.CreateInvoice;

import java.time.LocalDate;

import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.CreateInvoiceInputDTO;
import com.nhom3_221404.dto.CreateInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.exceptions.DateOutOfRangeException;
import com.nhom3_221404.exceptions.InternalDataAccessException;

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
        InvoiceType invoiceType = createInvoiceInputDTO.getInvoiceType();
        String roomId = createInvoiceInputDTO.getRoomId();
        Double price = createInvoiceInputDTO.getPrice();
        String customerName = createInvoiceInputDTO.getCustomerName();
        LocalDate billedDate = createInvoiceInputDTO.getBilledDate();

        if(!isWithinTwelveMonths(billedDate)) {
            createIOutputB.presentError(
                new DateOutOfRangeException()
            );
        }

        if (invoiceType == InvoiceType.Hourly && createInvoiceInputDTO.getRentalHours() > 30) {
            invoiceType = InvoiceType.Daily;
        }

        Invoice invoice = null;
        switch (invoiceType) {
            case Daily:
                invoice = new InvoiceDaily(createInvoiceInputDTO.getRentalDays());
                break;
            case Hourly:
                invoice = new InvoiceHourly(createInvoiceInputDTO.getRentalHours());
                break;
        }
        invoice.setRoomId(roomId);
        invoice.setPrice(price);
        invoice.setCustomerName(customerName);
        invoice.setBilledDate(billedDate);
        invoice.getTotal();

        Invoice newInvoice = createIDatabaseB.addInvoice(invoice);
        if(newInvoice == null) {
            createIOutputB.presentError(
                new InternalDataAccessException()
            );
            return;
        }

        CreateInvoiceOutputDTO response = new CreateInvoiceOutputDTO();
        response.setRoomId(newInvoice.getRoomId());
        response.setCustomerName(newInvoice.getCustomerName());
        response.setPrice(newInvoice.getPrice());
        response.setBilledDate(newInvoice.getBilledDate());
        response.setTotal(newInvoice.getTotal());

        createIOutputB.presentResult(response);
    }

    public boolean isWithinTwelveMonths(LocalDate date) {
        LocalDate today = LocalDate.now();
        LocalDate twelveMonthsAgo = today.minusMonths(12);

        return !date.isBefore(twelveMonthsAgo) && !date.isAfter(today);
    }
}
