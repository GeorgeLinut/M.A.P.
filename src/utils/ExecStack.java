package utils;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by glinut on 11/1/2016.
 */
public interface ExecStack<T> extends Serializable {
    void push(T elem);
    boolean isEmpty();
    T pop();
    T top();
    public Iterator<T> iterator();
}

