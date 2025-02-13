package com.filesystem.commad;

import com.filesystem.model.Directory;
import com.filesystem.model.FileSystem;
import com.filesystem.model.FileSystemEntity;
import com.filesystem.utils.Validator;

import java.util.Map;
import java.util.Objects;

public class MkdirCommand implements Command {

    private String subdirectoryName;
    private Validator validator;

    public MkdirCommand(String subdirectoryName, Validator validator){
        this.subdirectoryName = subdirectoryName;
        this.validator = validator;
    }
    @Override
    public String execute(FileSystem fileSystem) {
        String response = "";
        Directory current = fileSystem.getCurrentPosition();
        Map<String, FileSystemEntity> subdirectories = current.getChilds();
        if(Objects.isNull(subdirectories.get(this.subdirectoryName))){
            boolean isValidName = validator.validSubdirectoryName(this.subdirectoryName);
            if(isValidName){
                Directory directory = new Directory(this.subdirectoryName,current);
                current.addChild(directory);
            }
            else {
                response = "Invalid File or Folder Name";
            }
        }else{
            response = "Directory already exists";
        }
        return response;
    }
}
