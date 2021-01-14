package com.mulesoft.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class FileSystemEntity implements Serializable {

    private String name;
    private String absolutePath;
    private Directory parent;
    private LocalDateTime creationTime;
    private FileSystemEntityType type;

    public FileSystemEntity(String name, Directory parent, FileSystemEntityType type) {
        this.name = name;
        this.parent = parent;
        this.type = type;
        this.absolutePath = buildAbsolutePath(name,parent);
        this.creationTime = LocalDateTime.now();
    }

    private String buildAbsolutePath(String name, Directory parent){
        StringBuilder builder = new StringBuilder();
        //Check for root directory case
        if(Objects.nonNull(parent)){
            builder.append(parent.getAbsolutePath());
        }
        builder.append("/");
        builder.append(name);
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public Directory getParent() {
        return parent;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public FileSystemEntityType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FileSystemEntity))
            return false;
        FileSystemEntity entity = (FileSystemEntity) o;
        return Objects.equals(name, entity.name) &&
                Objects.equals(absolutePath, entity.absolutePath) &&
                Objects.equals(parent, entity.parent) &&
                Objects.equals(creationTime, entity.creationTime) &&
                type == entity.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, absolutePath, parent, creationTime, type);
    }
}
