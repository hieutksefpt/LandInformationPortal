/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.utils;

import java.util.Random;

/**
 *
 * @author AnhHao
 */
public class PasswordGenerator {

    private static final int leftLimit = 48;
    private static final int rightLimit = 122;

    private PasswordGenerator() {
    }

    public static String generate(int targetStringLength) {
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
