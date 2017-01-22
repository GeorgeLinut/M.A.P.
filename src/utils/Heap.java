package utils;

import groovy.util.MapEntry;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by glinut on 11/25/2016.
 */
public interface Heap<K,V> extends Serializable {
    public void add(K key,V value);
    public V getValue(K key);
    public void setValue(K key,V value);
    public void remove(K key);
    Iterable<Map.Entry<K,V>> getAll();
    public Iterator iterator();
}
