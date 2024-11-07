package com.nhom3_221404.database;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.usecase.CountInvoicesByRoom.CountInvoicesByRoomDatabaseBoundary;

public class CountInvoicesByRoomDAOMySQL implements CountInvoicesByRoomDatabaseBoundary {
    private InvoiceRepository invoiceRepository;

    public CountInvoicesByRoomDAOMySQL(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public int countInvoicesByRoom(String roomId) {
        return invoiceRepository.findAll().stream()
                .filter(invoice -> invoice.getRoomId().equals(roomId))
                .mapToInt(invoice -> 1)
                .sum();
    }
}