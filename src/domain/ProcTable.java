package domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by glinut on 1/22/2017.
 */
public interface ProcTable<K,V> extends Serializable {
    void add(K desc,V pair);
    V get(K desc);
    void remove(K desc);
    Iterable<Map.Entry<K,V>> getAll();
    public Iterator iterator();

}
