package com.nhom3_221404.usecase.UpdateInvoice;

import java.time.LocalDate;

import com.nhom3_221404.constant.StringConst;
import com.nhom3_221404.dto.UpdateInvoiceInputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.exceptions.DateOutOfRangeException;
import com.nhom3_221404.exceptions.InvoiceNotFoundException;
import com.nhom3_221404.exceptions.RentalHoursOutOfRangeException;

public class UpdateInvoiceUseCase implements UpdateInvoiceInputBoundary {
    private final UpdateInvoiceOutputBoundary updateOutputBoundary;
    private final UpdateInvoiceDatabaseBoundary updateDatabaseBoundary;

    public UpdateInvoiceUseCase(UpdateInvoiceOutputBoundary updateOutputBoundary,
            UpdateInvoiceDatabaseBoundary updateDatabaseBoundary) {
        this.updateOutputBoundary = updateOutputBoundary;
        this.updateDatabaseBoundary = updateDatabaseBoundary;
    }
    
    @Override
    public void execute(UpdateInvoiceInputDTO inputDto) {
        String invoiceType = inputDto.getInvoiceType();
        String id = inputDto.getId();
        Invoice invoice = updateDatabaseBoundary.getInvoiceById(id);
        if (invoice == null) {
            updateOutputBoundary.presentError(new InvoiceNotFoundException(id));
            return;
        }

        LocalDate billedDate = inputDto.getBilledDate();
        if (!isWithinTwelveMonths(billedDate)) {
            updateOutputBoundary.presentError(new DateOutOfRangeException());
            return;
        }

         if (invoiceType.equalsIgnoreCase("hourly")) {
            int rentalHours = inputDto.getRentalHours();
            if(rentalHours > 30) {
                updateOutputBoundary.presentError(new RentalHoursOutOfRangeException());
                return;
            }
        }

        invoice.setRoomId(inputDto.getRoomId());
        invoice.setPrice(inputDto.getPrice());
        invoice.setCustomerName(inputDto.getCustomerName());
        invoice.setBilledDate(billedDate);

        switch (invoiceType) {
            case "daily":
                InvoiceDaily invoiceDaily = (InvoiceDaily) invoice;
                invoiceDaily.setRentalDays(inputDto.getRentalDays());
                break;
            case "hourly":
                InvoiceHourly invoiceHourly = (InvoiceHourly) invoice;
                invoiceHourly.setRentalHours(inputDto.getRentalHours());
                break;
        }
        updateDatabaseBoundary.updateInvoice(invoice);

        updateOutputBoundary.presentResult(StringConst.SUCCESS_UPDATE_INVOICE);
    }

    private boolean isWithinTwelveMonths(LocalDate date) {
        LocalDate now = LocalDate.now();
        LocalDate twelveMonthsAgo = now.minusMonths(12);
        return !date.isBefore(twelveMonthsAgo) && !date.isAfter(now);
    }
}