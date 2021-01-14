package com.mulesoft.command;

import com.mulesoft.commad.CdCommand;
import com.mulesoft.commad.LsCommand;
import com.mulesoft.commad.MkdirCommand;
import com.mulesoft.model.FileSystem;
import com.mulesoft.utils.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LsCommandTest {

    public FileSystem fileSystem;


    @BeforeEach
    public void init(){
        fileSystem = new FileSystem();
        MkdirCommand mkdirCommand = new MkdirCommand("foo1",Validator.getInstance());
        mkdirCommand.execute(fileSystem);
        MkdirCommand mkdirCommand2 = new MkdirCommand("foo2",Validator.getInstance());
        mkdirCommand2.execute(fileSystem);
        MkdirCommand mkdirCommand3 = new MkdirCommand("foo3",Validator.getInstance());
        mkdirCommand3.execute(fileSystem);
        CdCommand cdCommand1 = new CdCommand("foo1");
        cdCommand1.execute(fileSystem);
        MkdirCommand mkdirCommandFoo2 = new MkdirCommand("foo2",Validator.getInstance());
        mkdirCommandFoo2.execute(fileSystem);
        MkdirCommand mkdirCommandFoo3 = new MkdirCommand("foo3",Validator.getInstance());
        mkdirCommandFoo3.execute(fileSystem);
        CdCommand upLevel = new CdCommand("..");
        upLevel.execute(fileSystem);
    }

    @Test
    public void lsRecursiveTest(){
        LsCommand lsCommand = new LsCommand("-r");
        String response = lsCommand.execute(fileSystem);
        String expectedResult = buildExpectedRecursiveValue();
        Assertions.assertEquals(expectedResult,response);
    }


    @Test
    public void lsNotRecursiveTest(){
        LsCommand lsCommand = new LsCommand("");
        String response = lsCommand.execute(fileSystem);
        String expectedResult = buildNotRecursiveValue();
        System.out.println(expectedResult);
        Assertions.assertEquals(expectedResult,response);
    }


    private String buildNotRecursiveValue(){
        StringBuilder builder = new StringBuilder();
        builder.append("foo1");
        builder.append("\n");
        builder.append("foo2");
        builder.append("\n");
        builder.append("foo3");
        return builder.toString();
    }

    private String buildExpectedRecursiveValue(){
        StringBuilder builder = new StringBuilder();
        builder.append("/root");
        builder.append("\n");
        builder.append("foo1");
        builder.append("\n");
        builder.append("foo2");
        builder.append("\n");
        builder.append("foo3");
        builder.append("\n");
        builder.append("/root/foo1");
        builder.append("\n");
        builder.append("foo2");
        builder.append("\n");
        builder.append("foo3");
        return builder.toString();
    }

}
