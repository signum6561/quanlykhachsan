package com.nhom3_221404.usecase.UpdateInvoice;

import java.time.LocalDate;

import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.dto.UpdateInvoiceInputDTO;
import com.nhom3_221404.dto.UpdateInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.exceptions.DateOutOfRangeException;
import com.nhom3_221404.exceptions.InternalDataAccessException;

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
        String id = updateInvoiceInputDTO.getId();
        Invoice existingInvoice = updateDatabaseBoundary.findInvoiceById(id);

        if (existingInvoice == null) {
            updateOutputBoundary.presentError(new InternalDataAccessException());
            return;
        }

        LocalDate billedDate = updateInvoiceInputDTO.getBilledDate();
        if (!isWithinTwelveMonths(billedDate)) {
            updateOutputBoundary.presentError(new DateOutOfRangeException());
            return;
        }

        existingInvoice.setRoomId(updateInvoiceInputDTO.getRoomId());
        existingInvoice.setPrice(updateInvoiceInputDTO.getPrice());
        existingInvoice.setCustomerName(updateInvoiceInputDTO.getCustomerName());
        existingInvoice.setBilledDate(billedDate);

        // Cập nhật số giờ hoặc số ngày thuê tùy theo loại hóa đơn
        if (existingInvoice instanceof InvoiceHourly) {
            ((InvoiceHourly) existingInvoice).setRentalHours(updateInvoiceInputDTO.getRentalHours());
        } else if (existingInvoice instanceof InvoiceDaily) {
            ((InvoiceDaily) existingInvoice).setRentalDays(updateInvoiceInputDTO.getRentalDays());
        }

        // Lưu hóa đơn đã cập nhật
        Invoice updatedInvoice = updateDatabaseBoundary.updateInvoice(existingInvoice);
        if (updatedInvoice == null) {
            updateOutputBoundary.presentError(new InternalDataAccessException());
            return;
        }

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