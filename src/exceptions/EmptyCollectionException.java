package exceptions;

/**
 * Created by glinut on 11/8/2016.
 */
public class EmptyCollectionException extends RuntimeException{
    public EmptyCollectionException(String message) {
        super(message);
    }
}
