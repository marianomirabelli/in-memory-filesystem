package com.mulesoft.commad;

import com.mulesoft.model.FileSystem;

public interface Command {

    String execute(FileSystem fileSystem);
}
