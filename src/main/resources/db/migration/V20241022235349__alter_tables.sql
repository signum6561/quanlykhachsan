ALTER TABLE Invoice
ADD CONSTRAINT chk_invoiceType CHECK (invoiceType IN ("dl", "hl"));

ALTER TABLE InvoiceDaily
ADD CONSTRAINT fk_iv_daily 
FOREIGN KEY (invoiceId) REFERENCES Invoice(id) ON DELETE CASCADE;

ALTER TABLE InvoiceHourly
ADD CONSTRAINT fk_iv_hourly 
FOREIGN KEY (invoiceId) REFERENCES Invoice(id) ON DELETE CASCADE;