ALTER TABLE invoice
ADD CONSTRAINT fk_iv_type 
FOREIGN KEY (invoice_type) REFERENCES invoice_type(code);

ALTER TABLE invoice_daily
ADD CONSTRAINT fk_iv_daily 
FOREIGN KEY (invoice_id) REFERENCES invoice(id) ON DELETE CASCADE;

ALTER TABLE invoice_hourly
ADD CONSTRAINT fk_iv_hourly 
FOREIGN KEY (invoice_id) REFERENCES invoice(id) ON DELETE CASCADE;

