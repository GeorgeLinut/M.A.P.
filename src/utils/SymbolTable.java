package utils;

import java.io.Serializable;

/**
 * Created by glinut on 11/1/2016.
 */
public interface SymbolTable<K,V> extends Serializable {
    public void add(K key,V value);
    public V getValue(K key);
    public boolean has(K key);
    public void setValue(K key,V Value);
    public int size();
    public SymbolTable<K,V> clone();
}
