package com.nhom3_221404.usecase.UpdateInvoice;

import java.time.LocalDate;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.UpdateInvoiceInputDTO;
import com.nhom3_221404.dto.UpdateInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;

public class UpdateInvoiceUseCase implements UpdateInvoiceInputBoundary {
    private UpdateInvoiceOutputBoundary updateOutputBoundary;
    private UpdateInvoiceDatabaseBoundary updateDatabaseBoundary;

    public UpdateInvoiceUseCase(UpdateInvoiceOutputBoundary updateOutputBoundary,
            UpdateInvoiceDatabaseBoundary updateDatabaseBoundary) {
        this.updateOutputBoundary = updateOutputBoundary;
        this.updateDatabaseBoundary = updateDatabaseBoundary;
    }
    @Override
    public void execute(UpdateInvoiceInputDTO updateInvoiceInputDTO) {
        InvoiceType invoiceType = updateInvoiceInputDTO.getInvoiceType();
        String id = updateInvoiceInputDTO.getId();
        Invoice existingInvoice = updateDatabaseBoundary.getInvoiceById(id);

        
        LocalDate billedDate = updateInvoiceInputDTO.getBilledDate();
        if (!isWithinTwelveMonths(billedDate)) {
            updateOutputBoundary.presentError(Errors.DateOutOfRange);
            return;
        }
        if (existingInvoice == null) {
            updateOutputBoundary.presentError(Errors.InvoiceNotFound);
            return;
        }

         if (invoiceType == InvoiceType.Hourly) {
            int rentalHours = updateInvoiceInputDTO.getRentalHours();
            if(rentalHours > 30) {
                updateOutputBoundary.presentError(Errors.RentalHoursOutOfRange);
                return;
            }
        }

        existingInvoice.setRoomId(updateInvoiceInputDTO.getRoomId());
        existingInvoice.setPrice(updateInvoiceInputDTO.getPrice());
        existingInvoice.setCustomerName(updateInvoiceInputDTO.getCustomerName());
        existingInvoice.setBilledDate(billedDate);

        if (existingInvoice instanceof InvoiceHourly) {
            ((InvoiceHourly) existingInvoice).setRentalHours(updateInvoiceInputDTO.getRentalHours());
        } else if (existingInvoice instanceof InvoiceDaily) {
            ((InvoiceDaily) existingInvoice).setRentalDays(updateInvoiceInputDTO.getRentalDays());
        }

        Invoice updatedInvoice = updateDatabaseBoundary.updateInvoice(existingInvoice);
        UpdateInvoiceOutputDTO response = new UpdateInvoiceOutputDTO();
        response.setId(updatedInvoice.getId());
        response.setRoomId(updatedInvoice.getRoomId());
        response.setCustomerName(updatedInvoice.getCustomerName());
        response.setPrice(updatedInvoice.getPrice());
        response.setBilledDate(updatedInvoice.getBilledDate());
        response.setTotal(updatedInvoice.getTotal());

        updateOutputBoundary.presentResult(response);
    }

    private boolean isWithinTwelveMonths(LocalDate date) {
        LocalDate now = LocalDate.now();
        LocalDate twelveMonthsAgo = now.minusMonths(12);
        return !date.isBefore(twelveMonthsAgo) && !date.isAfter(now);
    }
}