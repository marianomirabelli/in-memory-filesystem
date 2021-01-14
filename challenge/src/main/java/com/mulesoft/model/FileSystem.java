package com.mulesoft.model;

public class FileSystem {

    private Directory root;
    private Directory currentPosition;

    public FileSystem(){
        this.root = new Directory("root",null);
        this.currentPosition = root;
    }

    public void downLevel(String name){
        this.currentPosition = this.currentPosition.getSubdirectories().get(name);
    }

    public void upLevel(){
        this.currentPosition = this.currentPosition.getParent();
    }

    public Directory getRoot() {
        return root;
    }

    public Directory getCurrentPosition() {
        return currentPosition;
    }

}
