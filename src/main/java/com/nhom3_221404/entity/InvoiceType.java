package com.nhom3_221404.entity;

import java.util.List;

public class InvoiceType {
    private String code;
    private String name;
    private List<Invoice> invoices;

    public InvoiceType() {
    }

    public InvoiceType(String code) {
        this.code = code;
    }

    public InvoiceType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((invoices == null) ? 0 : invoices.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InvoiceType other = (InvoiceType) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (invoices == null) {
            if (other.invoices != null)
                return false;
        } else if (!invoices.equals(other.invoices))
            return false;
        return true;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public List<Invoice> getInvoices() {
        return invoices;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Integer getInvoiceCount() {
        return invoices.size();
    }
}
