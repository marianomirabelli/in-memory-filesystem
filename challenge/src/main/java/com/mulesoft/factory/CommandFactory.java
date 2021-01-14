package com.mulesoft.factory;

import com.mulesoft.commad.*;
import com.mulesoft.model.CommandType;
import com.mulesoft.utils.Validator;

import java.util.Optional;

public class CommandFactory {

    public Command createCommand(CommandType type, Optional<String> argument){
        switch (type){
        case TOUCH:
            return new TouchCommand(argument.get(), Validator.getInstance());
        case CD:
            return new CdCommand(argument.get());
        case PWD:
            return new PwdCommand();
        case LS:
            return new LsCommand(argument.orElse(""));
        case MKDIR:
            return new MkdirCommand(argument.get(),Validator.getInstance());
        default:
            throw new IllegalArgumentException("The command provided does not exist");
        }
    }



}
