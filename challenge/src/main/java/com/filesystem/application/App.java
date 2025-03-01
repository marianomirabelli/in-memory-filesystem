package com.filesystem.application;

import com.filesystem.controller.FileSystemController;
import com.filesystem.factory.CommandFactory;
import com.filesystem.model.FileSystem;
import com.filesystem.utils.Validator;

import java.util.Scanner;

public class App {


    private final FileSystemController controller;
    private final static String END_COMMAND = "quit";

    public App(){
        this.controller = new FileSystemController(new FileSystem(),new CommandFactory(), Validator.getInstance());
    }

    public  void run(){
        Scanner sc = new Scanner(System.in);
        String commandSentence = sc.nextLine();
        while (!commandSentence.equalsIgnoreCase(END_COMMAND)) {
            try {
                String commandOutput = controller.doExecute(commandSentence);
                System.out.print(commandOutput);
                System.out.print("\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            commandSentence = sc.nextLine();
        }

    }
}
