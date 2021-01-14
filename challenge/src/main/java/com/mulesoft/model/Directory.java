package com.mulesoft.model;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Directory extends FileSystemEntity {

    private Map<String,FileSystemEntity> childs;

    public Directory(String name,Directory parent){
        super(name,parent,FileSystemEntityType.DIRECTORY);
        this.childs = new TreeMap<>();
    }

    public void addChild(FileSystemEntity entity){
        this.childs.put(entity.getName(),entity);
    }

    public Map<String, FileSystemEntity> getChilds() {
        return childs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Directory))
            return false;
        if (!super.equals(o))
            return false;
        Directory directory = (Directory) o;
        return Objects.equals(childs, directory.childs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), childs);
    }
}
