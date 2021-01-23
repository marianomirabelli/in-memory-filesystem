package com.filesystem.commad;

import com.filesystem.model.FileSystem;

public class PwdCommand implements Command {

    @Override
    public String execute(FileSystem fileSystem) {
        String absolutePath = fileSystem.getCurrentPosition().getAbsolutePath();
        return absolutePath;
    }
}
