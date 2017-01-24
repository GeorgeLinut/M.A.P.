package utils;

/**
 * Created by glinut on 1/23/2017.
 */
public class LockAdressGenerator {
    private static Integer id=0;
    public static  Integer generateAdress(){
        return ++id;
    }
}
