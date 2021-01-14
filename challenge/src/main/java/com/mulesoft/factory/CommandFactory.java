package com.mulesoft.factory;

import com.mulesoft.commad.*;
import com.mulesoft.model.CommandType;

import java.util.Optional;

public class CommandFactory {

    public Command createCommand(CommandType type, Optional<String> argument){

        switch (type){
        case UP:
            return new UpCommand();
        case CD:
            return new CdCommand(argument.get());
        case DIR:
            return new DirCommand();
        case MKDIR:
            return new MkdirCommand(argument.get());
        default:
            throw new IllegalArgumentException("The command provided does not exist");
        }
    }

}
