package com.mulesoft.commad;

import com.mulesoft.model.Directory;
import com.mulesoft.model.FileSystem;
import com.mulesoft.model.FileSystemEntity;
import com.mulesoft.model.FileSystemEntityType;

import java.util.Objects;

public class CdCommand implements Command{

    private String argument;
    private final static String UP_DIRECTORY = "..";
    //private final static String MULTI_FACETED_PARAMETER = "-mf";
    public CdCommand(String argument){
        this.argument = argument;
    }

    @Override
    public String execute(FileSystem fileSystem) {
        String response = "";
        if(argument.equals(UP_DIRECTORY)){
            upLevel(fileSystem);
        }else{
            response = downLevel(fileSystem);
        }
        return response;
    }

    private void upLevel(FileSystem fileSystem){
        Directory current = fileSystem.getCurrentPosition();
        Directory root = fileSystem.getRoot();
        if(!current.equals(root)){
            fileSystem.upLevel();
        }
    }

    private String  downLevel(FileSystem fileSystem){
        String response = "Directory not found";
        FileSystemEntity child = fileSystem.getCurrentPosition().getChilds().get(this.argument);
        if(Objects.nonNull(child) && child.getType().equals(FileSystemEntityType.DIRECTORY)){
            Directory subdirectory = (Directory) child;
            fileSystem.downLevel(subdirectory);
            response ="";
        }
        return response;
    }

}
