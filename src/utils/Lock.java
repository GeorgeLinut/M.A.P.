package utils;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by glinut on 1/23/2017.
 */
public interface Lock<K,V> extends Serializable {
    public void add(K key,V value);
    public  V getValue(K key);
    public void setValue(K key,V value);
    public void remove(K key);
    Iterable<Map.Entry<K,V>> getAll();
    public Iterator iterator();
}
