package utils;

/**
 * Created by glinut on 12/2/2016.
 */
public class PrgStateIdGenerator {
    private static Integer id=0;
    public static Integer generate(){
        return ++id;
    }
}
