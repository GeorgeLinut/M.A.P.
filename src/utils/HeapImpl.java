package utils;

import exceptions.InterpretorException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by glinut on 11/25/2016.
 */
public class HeapImpl<K,V> implements Heap<K,V> {
    private HashMap<K,V> table;

    public HeapImpl() {
        this.table = new HashMap<K, V>();
    }

    @Override
    public void add(K key, V value) {
        table.put(key,value);
    }

    @Override
    public V getValue(K key) {
        return table.get(key);
    }

    @Override
    public void setValue(K key, V value) {
        if (table.containsKey(key)){
            table.put(key,value);
        }
        else {
            throw new InterpretorException("the address does not exist in the heap");
        }
    }

    public boolean has(K key) {
        return table.containsKey(key);
    }


    @Override
    public void remove(K key) {
        this.table.remove(key);
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return (Iterable<Map.Entry<K, V>>) this.table;
    }


    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (K key:table.keySet())
            buffer.append(" ( "+key+":"+this.getValue(key)+")");
        buffer.append("}");
        return buffer.toString();
    }

    public HashMap<K,V> all(){
        return this.table;
    }
}
