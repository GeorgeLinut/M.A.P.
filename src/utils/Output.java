package utils;

import java.io.Serializable;

/**
 * Created by glinut on 11/4/2016.
 */
public interface Output<T> extends Serializable{
    void add(T element);
    boolean isEmpty();
}
