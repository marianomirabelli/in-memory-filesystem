package com.filesystem.controller;

import com.filesystem.commad.CdCommand;
import com.filesystem.commad.MkdirCommand;
import com.filesystem.factory.CommandFactory;
import com.filesystem.model.FileSystem;
import com.filesystem.utils.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileSystemControllerTest {

    public FileSystem fileSystem;
    private FileSystemController controller;

    @BeforeEach
    public void init() {
        fileSystem = new FileSystem();
        MkdirCommand mkdirCommand = new MkdirCommand("foo1", Validator.getInstance());
        mkdirCommand.execute(fileSystem);
        MkdirCommand mkdirCommand2 = new MkdirCommand("foo2", Validator.getInstance());
        mkdirCommand2.execute(fileSystem);
        MkdirCommand mkdirCommand3 = new MkdirCommand("foo3", Validator.getInstance());
        mkdirCommand3.execute(fileSystem);
        CdCommand cdCommand1 = new CdCommand("foo1");
        cdCommand1.execute(fileSystem);
        MkdirCommand mkdirCommandFoo2 = new MkdirCommand("foo2", Validator.getInstance());
        mkdirCommandFoo2.execute(fileSystem);
        MkdirCommand mkdirCommandFoo3 = new MkdirCommand("foo3", Validator.getInstance());
        mkdirCommandFoo3.execute(fileSystem);
        CdCommand upLevel = new CdCommand("..");
        upLevel.execute(fileSystem);
        this.controller = new FileSystemController(fileSystem, new CommandFactory(), Validator.getInstance());
    }

    @Test
    public void executeUnexistentCommand() {
        String commandLine = "foo -r";
        String expectedOutput = "Unrecognized command";
        String response = this.controller.doExecute(commandLine);
        Assertions.assertEquals(expectedOutput, response);
    }

    @Test
    public void executePwdAction() {
        String commandLine = "pwd";
        String response = this.controller.doExecute(commandLine);
        Assertions.assertEquals("/root", response);
    }

    @Test
    public void executeCdWithoutValidDirectory() {
        String commandLine = "cd foo8";
        String response = this.controller.doExecute(commandLine);
        String expectedAnswer = "Directory not found";
        this.controller.doExecute("touch file1");
        String cdIntoFileResponse = this.controller.doExecute("cd foo8");
        Assertions.assertEquals(expectedAnswer, cdIntoFileResponse);
        Assertions.assertEquals(expectedAnswer, response);
    }

    @Test
    public void executeCdWithValidDirectory() {
        String commandLine = "cd foo2";
        String response = this.controller.doExecute(commandLine);
        String expectedAnswer = "";
        Assertions.assertEquals(expectedAnswer, response);
        String output = this.controller.doExecute("ls");
        Assertions.assertEquals("", output);
        this.controller.doExecute("cd ..");
    }

    @Test
    public void executeCdWithFile() {
        this.controller.doExecute("touch file1");
        String response = this.controller.doExecute("cd file1");
        String expectedAnswer = "Directory not found";
        Assertions.assertEquals(expectedAnswer, response);
    }

    @Test
    public void executeMkdir() {
        String commandLine = "mkdir foo4";
        String response = this.controller.doExecute(commandLine);
        String expectedAnswer = "";
        Assertions.assertEquals(expectedAnswer, response);
        String parentLevel = this.controller.doExecute("ls");
        StringBuilder expectedAnswerBuilder = new StringBuilder();
        expectedAnswerBuilder.append("foo1");
        expectedAnswerBuilder.append("\n");
        expectedAnswerBuilder.append("foo2");
        expectedAnswerBuilder.append("\n");
        expectedAnswerBuilder.append("foo3");
        expectedAnswerBuilder.append("\n");
        expectedAnswerBuilder.append("foo4");
        Assertions.assertEquals(expectedAnswerBuilder.toString(), parentLevel);
    }

    @Test
    public void executeMkdirInvalidName() {
        String commandLine = "mkdir foo411111212121212121212121212121212121212121212121111111111111111111111111111121212121212121212121212";
        String response = this.controller.doExecute(commandLine);
        String expectedAnswer = "Invalid File or Folder Name";
        Assertions.assertEquals(expectedAnswer, response);
    }

    @Test
    public void executeMkdirDuplicatedName() {
        String commandLine = "mkdir foo1";
        String response = this.controller.doExecute(commandLine);
        String expectedAnswer = "Directory already exists";
        Assertions.assertEquals(expectedAnswer, response);
    }

    @Test
    public void executeTouchInvalidName() {
        String commandLine = "touch foo411111212121212121212121212121212121212121212121111111111111111111111111111121212121212121212121212";
        String response = this.controller.doExecute(commandLine);
        String expectedAnswer = "Invalid File or Folder Name";
        Assertions.assertEquals(expectedAnswer, response);
    }

    @Test
    public void executeTouchDuplicatedName() {
        this.controller.doExecute("touch file1");
        String response = this.controller.doExecute("touch file1");
        String expectedAnswer = "File already exists";
        Assertions.assertEquals(expectedAnswer, response);
    }

    @Test
    public void executeTouch() {
        String commandLine = "touch file1";
        String response = this.controller.doExecute(commandLine);
        String expectedAnswer = "";
        Assertions.assertEquals(expectedAnswer, response);
        String parentLevel = this.controller.doExecute("ls");
        StringBuilder expectedAnswerBuilder = new StringBuilder();
        expectedAnswerBuilder.append("file1");
        expectedAnswerBuilder.append("\n");
        expectedAnswerBuilder.append("foo1");
        expectedAnswerBuilder.append("\n");
        expectedAnswerBuilder.append("foo2");
        expectedAnswerBuilder.append("\n");
        expectedAnswerBuilder.append("foo3");
        Assertions.assertEquals(expectedAnswerBuilder.toString(), parentLevel);
    }

}
