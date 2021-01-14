package com.mulesoft.commad;

import com.mulesoft.model.Directory;
import com.mulesoft.model.FileSystem;

public class UpCommand implements Command {

    @Override
    public String execute(FileSystem fileSystem) {
        String response = "";
        Directory current = fileSystem.getCurrentPosition();
        Directory root = fileSystem.getRoot();
        if(current.equals(root)){
            response = "Can not move up from root directory";
        }else{
            fileSystem.upLevel();
        }
        return response;
    }
}
