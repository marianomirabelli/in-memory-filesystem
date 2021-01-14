package com.mulesoft.utils;

import java.util.HashSet;
import java.util.Set;

public class Validator {

    private final static int UPPER_BOUND = 100;
    private static Validator instance;
    private final Set<String> validCommands;

    public static Validator getInstance(){
        if(instance==null){
            instance = new Validator();
        }
        return instance;
    }

    Validator(){
        this.validCommands = new HashSet();
        this.validCommands.add("ls");
        this.validCommands.add("mkdir");
        this.validCommands.add("pwd");
        this.validCommands.add("touch");
        this.validCommands.add("cd");
    }

    public boolean validSubdirectoryName(String name) {
        if (name.length() > UPPER_BOUND) {
            return false;
        }
        return true;
    }

    public boolean validCommandInput(String command){
        return validCommands.contains(command);

    }

    public static int getUpperBound() {
        return UPPER_BOUND;
    }
}
