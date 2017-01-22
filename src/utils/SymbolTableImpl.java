package utils;

import utils.SymbolTable;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by glinut on 11/1/2016.
 */
public class SymbolTableImpl<K,V> implements SymbolTable<K,V> {
    private HashMap<K,V> table;

    public SymbolTableImpl() {
        this.table = new HashMap<K, V>();
    }

    @Override
    public void add(K key, V value) {
            table.put(key,value);
    }

    @Override
    public V getValue(K key) throws Error {
        V value=table.get(key);
//        if (value==null){
//            throw new Error("key doesn't exist in the table");
//        }
        return value;
    }

    @Override
    public boolean has(K key) {
        return table.containsKey(key);
    }

    @Override
    public void setValue(K key, V Value) {
        table.put(key,Value);
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public SymbolTable<K, V> clone() {
        SymbolTable<K,V> newTable = new SymbolTableImpl<K, V>();
        for(K key:table.keySet()){
            V value = table.get(key);
            newTable.add(key,value);
        }
        return newTable;
    }

    @Override
    public Iterator iterator() {
        return table.entrySet().iterator();
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

    public HashMap<K,V> getAll(){
        return this.table;
    }
}
