package com.isep.acme.products.model.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Profile("sku2")
@Service
public class SkuGeneratorAlgLettSpecialChar implements ISkuGenerator {

    public SkuGeneratorAlgLettSpecialChar() {
    }

    @Override()
    public String generate(String designation) {
        try {
            return generateSKU();
        } catch (Exception e) {
            return null;
        }
    }

    public static String generateSKU() {
        String algarisms = "0123456789";
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$%^&*()_+";

        Random random = new SecureRandom();

        StringBuilder sb = new StringBuilder();
        sb.append(getRandomCharacter(algarisms, random));
        sb.append(getRandomCharacter(letters, random));
        sb.append(getRandomCharacter(algarisms, random));
        sb.append(getRandomCharacter(letters, random));
        sb.append(getRandomCharacter(algarisms, random));
        sb.append(getRandomCharacter(letters, random));
        sb.append(getRandomCharacter(letters, random));
        sb.append(getRandomCharacter(algarisms, random));
        sb.append(getRandomCharacter(letters, random));
        sb.append(getRandomCharacter(specialCharacters, random));

        return sb.toString();
    }

    /**
     * Picks a random leter/algarism/special character from the ones defined in the generateSKU method
     *
     * @param characters
     * @param random
     * @return
     */
    private static char getRandomCharacter(String characters, Random random) {
        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }

}
