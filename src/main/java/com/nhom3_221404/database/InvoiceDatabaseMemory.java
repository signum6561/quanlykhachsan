package com.nhom3_221404.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nhom3_221404.entity.Invoice;

public class InvoiceDatabaseMemory {
    private Map<String, Invoice> database;

    public InvoiceDatabaseMemory() {
        database = new HashMap<>();
    }

    public List<Invoice> findAll() {
        return new ArrayList<>(database.values());
    }

    public Invoice findById(String id) {
        return database.get(id);
    }

    public void save(Invoice invoice) {
        database.put(invoice.getId(), invoice);
    }

    public void delete(String id) {
        database.remove(id);
    } 

    public void deleteAll() {
        database.clear();
    }

}
