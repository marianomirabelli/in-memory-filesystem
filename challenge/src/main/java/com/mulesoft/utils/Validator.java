package com.mulesoft.utils;

import java.util.regex.Pattern;

public class Validator {

    private final static int LOWER_BOUND = 1;
    private final static int UPPER_BOUND = 6;
    private final static String SUBDIRECTORY_NAME_REGEX = "^[A-Za-z0-9_]*$";

    public static void validateSubdirectoryName(String name) {
        if (name.length() < LOWER_BOUND || name.length() > UPPER_BOUND) {
            String message = String.format("The name length should be ranged between %d %d", LOWER_BOUND, UPPER_BOUND);
            throw new IllegalArgumentException(message);
        }
        Pattern pattern = Pattern.compile(SUBDIRECTORY_NAME_REGEX);
        if(!pattern.matcher(name).matches()){
            String message = String.format("The name should", LOWER_BOUND, UPPER_BOUND);
            throw new IllegalArgumentException(message);
        }


    }




}
