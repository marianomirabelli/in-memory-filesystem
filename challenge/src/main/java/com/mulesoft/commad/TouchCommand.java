package com.mulesoft.commad;

import com.mulesoft.model.*;
import com.mulesoft.utils.Validator;

import java.util.Map;
import java.util.Objects;

public class TouchCommand implements Command {

    private String fileName;
    private Validator validator;

    public TouchCommand(String fileName, Validator validator){
        this.fileName = fileName;
        this.validator = validator;
    }

    @Override
    public String execute(FileSystem fileSystem) {
        String response = "";
        Directory current = fileSystem.getCurrentPosition();
        Map<String, FileSystemEntity> subdirectories = current.getChilds();
        if(Objects.isNull(subdirectories.get(this.fileName))){
            boolean isValidName = validator.validSubdirectoryName(this.fileName);
            if(isValidName){
                File file = new File(this.fileName, current, FileSystemEntityType.FILE);
                current.addChild(file);
            }else {
                response = "Invalid File or Folder Name";
            }
        }else{
            response = "File already exists";
        }

        return response;
    }
}
