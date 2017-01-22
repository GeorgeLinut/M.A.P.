package utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by glinut on 11/15/2016.
 */
public class FileTableImpl<K,V> implements FileTable<K,V> {
    private HashMap<K,V> table;

    public FileTableImpl() {
        this.table = new HashMap<>();
    }

    @Override
    public void add(K desc, V pair) {
        this.table.put(desc,pair);
    }

    @Override
    public V get(K desc) {
        return this.table.get(desc);
    }

    @Override
    public void remove(K desc) {
        this.table.remove(desc);
    }

    public Iterator iterator(){
        return this.table.entrySet().iterator();
    };

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {

        return (Iterable<Map.Entry<K, V>>) table;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (K key:table.keySet())
            buffer.append(" ( "+key+":"+this.get(key)+")");
        buffer.append("}");
        return buffer.toString();
    }

}
