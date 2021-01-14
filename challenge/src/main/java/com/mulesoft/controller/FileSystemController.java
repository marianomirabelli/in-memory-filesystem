package com.mulesoft.controller;

import com.mulesoft.commad.Command;
import com.mulesoft.factory.CommandFactory;
import com.mulesoft.model.CommandType;
import com.mulesoft.model.FileSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileSystemController {

    private final FileSystem fileSystem;
    private final CommandFactory factory;
    private final static String COMMAND_CONSTANT = "Command: ";

    public FileSystemController(FileSystem fileSystem, CommandFactory factory){
       this.fileSystem = fileSystem;
       this.factory = factory;
    }

    public List<String> doExecute(List<String> commands){
        StringBuilder builder;
        List<String> commandResults = new ArrayList<>(commands.size());
        for(String executionLine: commands){
            builder = new StringBuilder();
            String [] arrayExecutionLine = executionLine.split("\\s+");
            String fileSystemCommand = arrayExecutionLine[0];
            Optional<String> argument = Optional.empty();
            if(arrayExecutionLine.length>1){
                argument = Optional.of(arrayExecutionLine[1]);
            }
            Command command = factory.createCommand(CommandType.valueOf(fileSystemCommand.toUpperCase()),argument);
            String response = command.execute(fileSystem);
            builder.append(COMMAND_CONSTANT);
            builder.append(executionLine);
            builder.append('\r');
            builder.append(response);
            commandResults.add(builder.toString());
        }

        return commandResults;
    }
}
