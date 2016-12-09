package utils;

/**
 * Created by glinut on 11/9/2016.
 */
public class FileIdGenerator {
    private static Integer id=2;
    public static Integer generateId(){
        return ++id;
    }
}
