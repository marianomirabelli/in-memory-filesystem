package com.mulesoft.commad;

import com.mulesoft.model.FileSystem;

public class PwdCommand implements Command {

    @Override
    public String execute(FileSystem fileSystem) {
        String absolutePath = fileSystem.getCurrentPosition().getAbsolutePath();
        return absolutePath;
    }
}
