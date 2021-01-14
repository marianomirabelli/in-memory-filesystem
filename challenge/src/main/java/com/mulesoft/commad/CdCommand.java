package com.mulesoft.commad;

import com.mulesoft.model.Directory;
import com.mulesoft.model.FileSystem;

import java.util.Objects;

public class CdCommand implements Command{

    private String subdirectoryName;
    public CdCommand(String subdirectoryName){
        this.subdirectoryName = subdirectoryName;
    }

    @Override
    public String execute(FileSystem fileSystem) {
        String response = "";
        Directory subDirectory = fileSystem.getCurrentPosition().getSubdirectories().get(this.subdirectoryName);
        if(Objects.nonNull(subDirectory)){
            fileSystem.downLevel(this.subdirectoryName);
        }else{
            response = "Subdirectory does not exists";
        }
        return response;
    }


}
