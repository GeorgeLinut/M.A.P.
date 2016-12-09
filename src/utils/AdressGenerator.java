package utils;

/**
 * Created by glinut on 11/25/2016.
 */
public class AdressGenerator {
    private static Integer id=0;
    public static Integer generateAdress(){
        return ++id;
    }
}
