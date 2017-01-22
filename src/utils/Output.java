package utils;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by glinut on 11/4/2016.
 */
public interface Output<T> extends Serializable{
    void add(T element);
    boolean isEmpty();
    public Iterator<T> iterator();
}
