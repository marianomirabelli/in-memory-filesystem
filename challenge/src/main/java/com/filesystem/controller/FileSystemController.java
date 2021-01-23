package com.filesystem.controller;

import com.filesystem.commad.Command;
import com.filesystem.factory.CommandFactory;
import com.filesystem.model.CommandType;
import com.filesystem.model.FileSystem;
import com.filesystem.utils.Validator;

import java.util.Optional;

public class FileSystemController {

    private final FileSystem fileSystem;
    private final CommandFactory factory;
    private final Validator validator;

    public FileSystemController(FileSystem fileSystem, CommandFactory factory,Validator validator){
       this.fileSystem = fileSystem;
       this.factory = factory;
       this.validator = validator;
    }

    public String doExecute(String executionLine){
        StringBuilder builder = new StringBuilder();
        String [] arrayExecutionLine = executionLine.split("\\s+");
        String fileSystemCommand = arrayExecutionLine[0];
        Optional<String> argument = Optional.empty();
        if(arrayExecutionLine.length>1){
             argument = Optional.of(executionLine.substring(fileSystemCommand.length()+1));
        }
        if(validator.validCommandInput(fileSystemCommand)){
            Command command = factory.createCommand(CommandType.valueOf(fileSystemCommand.toUpperCase()),argument);
            String response = command.execute(fileSystem);
            builder.append(response);
        }else{
            builder.append("Unrecognized command");
        }
        return builder.toString();
    }

}
