package com.mulesoft;

import com.mulesoft.application.App;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Solution
{


    private final static String INPUT_FILE = "/src/main/resources/prog5.dat";
    private final static String OUTPUT_FILE = "prog5.out";

    public static void main( String[] args ) throws IOException {
        App.run(INPUT_FILE, OUTPUT_FILE);
    }
}
