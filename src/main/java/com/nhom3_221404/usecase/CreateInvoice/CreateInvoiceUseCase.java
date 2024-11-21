package com.nhom3_221404.usecase.CreateInvoice;

import java.time.LocalDate;

import com.nhom3_221404.constant.StringConst;
import com.nhom3_221404.dto.CreateInvoiceInputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.exceptions.DateOutOfRangeException;
import com.nhom3_221404.exceptions.RentalHoursOutOfRangeException;
import com.nhom3_221404.usecase.GenerateId.GenerateIdInputBoundary;
import com.nhom3_221404.usecase.GenerateId.GenerateIdOutputBoundary;

public class CreateInvoiceUseCase implements CreateInvoiceInputBoundary {
    private final CreateInvoiceOutputBoundary createIOutputB;
    private final CreateInvoiceDatabaseBoundary createIDatabaseB;
    private final GenerateIdInputBoundary generateIdInputB;
    private final GenerateIdOutputBoundary generateIdOutputB;

    private static final String PREFIX = "IV";

    public CreateInvoiceUseCase(CreateInvoiceOutputBoundary createIOutputB,
        CreateInvoiceDatabaseBoundary createIDatabaseB, GenerateIdInputBoundary generateIdInputB,
        GenerateIdOutputBoundary generateIdOutputB) {   
            this.createIOutputB = createIOutputB;
            this.createIDatabaseB = createIDatabaseB;
            this.generateIdInputB = generateIdInputB;
            this.generateIdOutputB = generateIdOutputB;
    }

    @Override
    public void execute(CreateInvoiceInputDTO inputDTO) {
        String invoiceType = inputDTO.getInvoiceType();
        LocalDate billedDate = inputDTO.getBilledDate();

        if (!isWithinTwelveMonths(billedDate)) {
            createIOutputB.presentError(new DateOutOfRangeException());
            return;
        }

        if (invoiceType.equalsIgnoreCase("hourly")) {
            int rentalHours = inputDTO.getRentalHours();
            if (rentalHours > 30) {
                createIOutputB.presentError(new RentalHoursOutOfRangeException());
                return;
            }
        }

        generateIdInputB.execute(PREFIX);
        String generatedId = generateIdOutputB.getGeneratedId();
        Invoice invoice = convertToEntity(inputDTO);
        invoice.setId(generatedId);
        
        createIDatabaseB.createInvoice(invoice);

        createIOutputB.presentSuccess(StringConst.SUCCESS_CREATE_INVOICE);
    }

    private Invoice convertToEntity(CreateInvoiceInputDTO inputDto) {
        String invoiceType = inputDto.getInvoiceType();
        Invoice invoice;
        switch (invoiceType.toLowerCase()) {
            case "daily":
                invoice = new InvoiceDaily(inputDto.getRentalDays());
                break;
            case "hourly":
                invoice = new InvoiceHourly(inputDto.getRentalHours());
                break;
            default:
                return null;
        }
        invoice.setRoomId(inputDto.getRoomId());
        invoice.setPrice(inputDto.getPrice());
        invoice.setCustomerName(inputDto.getCustomerName());
        invoice.setBilledDate(inputDto.getBilledDate());
        return invoice;
    }

    public boolean isWithinTwelveMonths(LocalDate date) {
        LocalDate today = LocalDate.now();
        LocalDate twelveMonthsAgo = today.minusMonths(12);
        LocalDate nextTwelveMonth = today.plusMonths(12);

        return date.isAfter(twelveMonthsAgo) && date.isBefore(nextTwelveMonth);
    }
}
