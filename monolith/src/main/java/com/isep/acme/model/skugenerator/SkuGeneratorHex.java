package com.isep.acme.model.skugenerator;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Profile("sku1")
@Service
public class SkuGeneratorHex implements ISkuGenerator {

    public SkuGeneratorHex() {
    }

    @Override()
    public String generate(String designation) {
        try {
            String hex = createHex(designation);

            return createSku(hex);
        } catch (Exception e) {
            return null;
        }
    }

    // Tirado de https://www.baeldung.com/sha-256-hashing-java
    private String createHex(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * hash.length);

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    private String createSku(String hex) {
        int padding = (hex.length() - 10) / 2;

        return hex.substring(padding, padding + 10);
    }
}
