package view;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by glinut on 11/15/2016.
 */
public abstract class Command {
    private String key,description;

    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public abstract void execute() throws FileNotFoundException, UnsupportedEncodingException;

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
