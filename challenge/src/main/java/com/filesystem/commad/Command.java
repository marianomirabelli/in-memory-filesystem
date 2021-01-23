package com.filesystem.commad;

import com.filesystem.model.FileSystem;

public interface Command {

    String execute(FileSystem fileSystem);
}
