package com.mulesoft.application;

import com.mulesoft.controller.FileSystemController;
import com.mulesoft.factory.CommandFactory;
import com.mulesoft.model.FileSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {

    public static void run(String inputFileName, String outputFileName){
        FileSystemController fileSystemController = new FileSystemController(new FileSystem(),new CommandFactory());
        try {
            List<String> commands = Files.readAllLines(Paths.get(System.getProperty("user.dir")+inputFileName));
            List<String> executionResult = fileSystemController.doExecute(commands);
            Files.write(Paths.get(System.getProperty("user.dir")+outputFileName),executionResult);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read/write files");
        }
    }
}
