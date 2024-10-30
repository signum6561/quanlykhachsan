package com.nhom3_221404.util;

import com.nhom3_221404.usecase.CreateInvoice.IdGeneratorBoundary;
import java.util.Random;

public class InvoiceIdGenerator implements IdGeneratorBoundary {
    final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    final static int DEFAULT_LENGTH = 6;
    final static String PREFIX = "IV";

    private String generateFromPattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            int index = random.nextInt(pattern.length());
                char randomChar = pattern.charAt(index);
                sb.append(randomChar);
        }
        return sb.toString();
    }

    @Override
    public String generate() {
        String randomString1 = generateFromPattern(ALPHABET);
        String randomString2 = generateFromPattern(ALPHABET);
        
        return String.join("-", PREFIX, randomString1, randomString2);
    }

}
