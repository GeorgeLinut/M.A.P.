package utils;

import exceptions.InterpretorException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by glinut on 1/23/2017.
 */
public class LockImpl<K,V> implements Lock<K,V> {
    private boolean available;
    private HashMap<K,V> table;

    public LockImpl() {
        this.table = new HashMap<K, V>();
        this.available =true;
    }

    @Override
    public synchronized void add(K key, V value) {
        this.table.put(key,value);
    }

    @Override
    public synchronized V getValue(K key) {
        while(available==false) {
            try {
                this.wait();
            }
            catch (InterruptedException e){}
        }
        available = false;
        notifyAll();
        V value = this.table.get(key);
        available = true;
        return value;
    }

    @Override
    public synchronized void setValue(K key, V value) {
        while (available==false){
            try {
                this.wait();
            }
            catch (InterruptedException e){

            }
        }
        available = false;
        if (table.containsKey(key)){
            table.put(key,value);
        }
        else {
            available = true;
            throw new InterpretorException("the address does not exist in the heap");
        }
        available = true;
    }

    @Override
    public void remove(K key) {
        this.table.remove(key);
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return (Iterable<Map.Entry<K,V>>)this.table;
    }

    @Override
    public Iterator iterator() {
        return this.table.entrySet().iterator();
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

}
