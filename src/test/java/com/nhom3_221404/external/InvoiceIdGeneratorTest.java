package com.nhom3_221404.external;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nhom3_221404.util.InvoiceIdGenerator;

public class InvoiceIdGeneratorTest {
    InvoiceIdGenerator idGenerator;

    @BeforeEach
    void setUp() {
        idGenerator = new InvoiceIdGenerator();
    }
    
    @Test
    void testGenerateId() {
        String res = idGenerator.generate();
        System.out.println(res);
    }
}
