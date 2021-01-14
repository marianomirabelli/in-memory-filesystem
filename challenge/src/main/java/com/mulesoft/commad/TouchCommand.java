package com.mulesoft.commad;

import com.mulesoft.model.Directory;
import com.mulesoft.model.File;
import com.mulesoft.model.FileSystem;
import com.mulesoft.model.FileSystemEntityType;
import com.mulesoft.utils.Validator;

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
        boolean isValidName = validator.validSubdirectoryName(this.fileName);
        if(isValidName){
            File file = new File(this.fileName, current, FileSystemEntityType.FILE);
            current.addChild(file);
        }else {
            response = "Invalid File or Folder Name";
        }
        return response;
    }
}
