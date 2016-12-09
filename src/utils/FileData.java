package utils;

import java.io.BufferedReader;
import java.io.Serializable;

/**
 * Created by glinut on 11/9/2016.
 */
public class FileData implements Serializable{
    private String filename;
    private BufferedReader reader;

    public FileData(String filename, BufferedReader reader) {
        this.filename = filename;
        this.reader = reader;
    }

    public String getFilename() {
        return filename;
    }

    public BufferedReader getReader() {
        return reader;
    }
}
