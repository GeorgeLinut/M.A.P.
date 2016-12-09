package exceptions;

/**
 * Created by glinut on 11/8/2016.
 */
public class VariableNotFoundException extends RuntimeException {
    public VariableNotFoundException(String message) {
        super(message);
    }
}
