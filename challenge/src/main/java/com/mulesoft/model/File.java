package com.mulesoft.model;

import java.util.Objects;

public class File extends FileSystemEntity {

    private String content;

    public File(String name, Directory parent, FileSystemEntityType type) {
        super(name, parent, type);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof File))
            return false;
        if (!super.equals(o))
            return false;
        File file = (File) o;
        return Objects.equals(content, file.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), content);
    }
}
