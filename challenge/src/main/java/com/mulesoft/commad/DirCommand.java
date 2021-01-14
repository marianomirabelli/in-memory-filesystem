package com.mulesoft.commad;

import com.mulesoft.model.Directory;
import com.mulesoft.model.FileSystem;

import java.util.Map;

public class DirCommand implements Command {

    private final static int LINE_HOP = 10;
    private final static int LEFT_JUSTIFICATION = 8;

    @Override
    public String execute(FileSystem fileSystem) {
        StringBuilder builder = new StringBuilder();
        String message = String.format("Directory of %s: \n",fileSystem.getCurrentPosition().getAbsolutePath());
        builder.append(message);
        Map<String, Directory> subdirectories = fileSystem.getCurrentPosition().getSubdirectories();
        if(!subdirectories.isEmpty()){
            int lines = 0;
            for(String childName: subdirectories.keySet()){
                int justification = LEFT_JUSTIFICATION - childName.length();
                String tab = String.format("%"+justification+"s","\b");
                builder.append(childName);
                builder.append(tab);
                lines++;
                if(lines>(LINE_HOP-1)){
                    builder.append("\n");
                    lines = 0;
                }
            }
        }else{
            builder.append("No subdirectories");
        }
        return builder.toString();
    }
}
