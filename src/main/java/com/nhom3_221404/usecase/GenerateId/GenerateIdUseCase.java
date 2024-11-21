package com.nhom3_221404.usecase.GenerateId;

import java.util.Random;

public class GenerateIdUseCase implements GenerateIdInputBoundary {
    private final GenerateIdOutputBoundary generateIdOutputB;

    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int DEFAULT_LENGTH = 6;

    public GenerateIdUseCase(GenerateIdOutputBoundary generateIdOutputB) {
        this.generateIdOutputB = generateIdOutputB;
    }

    @Override
    public void execute(String prefix) {
        String randomString1 = generateFromPattern();
        String randomString2 = generateFromPattern();
        
        String generatedId = String.join("-", prefix, randomString1, randomString2);
        generateIdOutputB.present(generatedId);
    }

    private String generateFromPattern() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            int index = random.nextInt(ALPHABET.length());
                char randomChar = ALPHABET.charAt(index);
                sb.append(randomChar);
        }
        return sb.toString();
    }
}
