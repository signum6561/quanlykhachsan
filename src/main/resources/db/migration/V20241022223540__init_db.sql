CREATE DATABASE IF NOT EXISTS quanlykhachsan;
USE quanlykhachsan;

CREATE TABLE Invoice (
    id VARCHAR(255) PRIMARY KEY,
    invoiceType VARCHAR(10),
    roomId VARCHAR(255) NOT NULL,
    price DECIMAL(12, 2) NOT NULL,
    customerName VARCHAR(255) NOT NULL,
    billedDate DATETIME NOT NULL
);

CREATE TABLE InvoiceHourly (
    invoiceId VARCHAR(255) PRIMARY KEY,
    rentalHours BIGINT NOT NULL,
    FOREIGN KEY (invoiceId) REFERENCES invoice(id)
);

CREATE TABLE InvoiceDaily (
    invoiceId VARCHAR(255) PRIMARY KEY,
    rentalDays BIGINT NOT NULL,
    FOREIGN KEY (invoiceId) REFERENCES invoice(id)
);