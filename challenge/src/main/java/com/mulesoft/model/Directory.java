package com.mulesoft.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Directory {

    private Directory parent;
    private LocalDateTime creationTime;
    private Map<String,Directory> subdirectories;
    private String absolutePath;
    private String name;

    public Directory(String name,Directory parent){
        this.subdirectories = new TreeMap<>();
        this.name = name;
        this.parent = parent;
        this.absolutePath = buildAbsolutePath(name,parent);
        this.creationTime = LocalDateTime.now();
    }

    private String buildAbsolutePath(String name, Directory parent){
        StringBuilder builder = new StringBuilder();
        //Check for root directory case
        if(Objects.nonNull(parent)){
            builder.append(parent.getAbsolutePath());
            builder.append("\\");
        }
        builder.append(name);
        return builder.toString();
    }

    public Directory getParent() {
        return parent;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Map<String, Directory> getSubdirectories() {
        return subdirectories;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public String getName() {
        return name;
    }

    public void addSubdirectory(String name){
        Directory subdirectory = new Directory(name,this);
        this.subdirectories.put(name,subdirectory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Directory))
            return false;
        Directory directory = (Directory) o;
        return Objects.equals(parent, directory.parent) &&
                Objects.equals(creationTime, directory.creationTime) &&
                Objects.equals(subdirectories, directory.subdirectories) &&
                Objects.equals(absolutePath, directory.absolutePath) &&
                Objects.equals(name, directory.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, creationTime, subdirectories, absolutePath, name);
    }
}
