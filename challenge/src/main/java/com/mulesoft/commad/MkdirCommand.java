package com.mulesoft.commad;

import com.mulesoft.model.Directory;
import com.mulesoft.model.FileSystem;
import com.mulesoft.utils.Validator;

import java.util.Map;
import java.util.Objects;

public class MkdirCommand implements Command {

    private String subdirectoryName;

    public MkdirCommand(String subdirectoryName){
        this.subdirectoryName = subdirectoryName;
    }
    @Override
    public String execute(FileSystem fileSystem) {
        String response = "";
        Directory current = fileSystem.getCurrentPosition();
        Map<String,Directory> subdirectories = current.getSubdirectories();
        if(Objects.isNull(subdirectories.get(this.subdirectoryName))){
            Validator.validateSubdirectoryName(this.subdirectoryName);
            current.addSubdirectory(this.subdirectoryName);
        }else{
            response = "Subdirectory already exists";
        }
        return response;
    }
}
