package com.filesystem.commad;

import com.filesystem.model.Directory;
import com.filesystem.model.FileSystem;
import com.filesystem.model.FileSystemEntity;
import com.filesystem.model.FileSystemEntityType;

import java.util.Map;
import java.util.TreeMap;

public class LsCommand implements Command {

    private final static String RECURSIVE = "-r";
    private String argument;

    public LsCommand(String argument){
        this.argument = argument;
    }

    @Override
    public String execute(FileSystem fileSystem) {
        StringBuilder builder = new StringBuilder();
        String response = "";
        Map<String, FileSystemEntity> subdirectories = fileSystem.getCurrentPosition().getChilds();
        if(!subdirectories.isEmpty()){
            if(argument.contains(RECURSIVE)){
                printPathsRecursive(subdirectories,builder);
            }else{
                printNotRecursive(subdirectories,builder);
            }
            builder.setLength(builder.length()-1);
            response = builder.toString();
        }
        return response;
    }


    private String printPathsRecursive(Map<String,FileSystemEntity> levelElements, StringBuilder builder){
        FileSystemEntity firstElement = levelElements.values().stream().findFirst().get();
        builder.append(firstElement.getParent().getAbsolutePath());
        builder.append("\n");
        Map<String,FileSystemEntity> childElements = new TreeMap<>();
        for(String entityName: levelElements.keySet()){
           builder.append(entityName);
           builder.append("\n");
           FileSystemEntity entity = levelElements.get(entityName);
           if(entity.getType().equals(FileSystemEntityType.DIRECTORY)){
               Directory dir = (Directory)entity;
               childElements.putAll(dir.getChilds());
           }
        }
       if(!childElements.isEmpty()){
            printPathsRecursive(childElements,builder);
        }
        return builder.toString();
    }

    private String printNotRecursive(Map<String,FileSystemEntity> subdirectories, StringBuilder builder){
        for(String entityName: subdirectories.keySet()){
            builder.append(entityName);
            builder.append("\n");
        }
        return builder.toString();
    }

}
