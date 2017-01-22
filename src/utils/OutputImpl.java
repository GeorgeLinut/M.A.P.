package utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by glinut on 11/4/2016.
 */
public class OutputImpl<T> implements Output<T> {
    private ArrayList<T> out;

    public OutputImpl() {
        this.out=new ArrayList<T>();
    }

    @Override
    public void add(T element) {
        out.add(element);
    }
    public String toString(){
        return out.toString();
    }

    @Override
    public boolean isEmpty(){
        return out.isEmpty();
    }

    public ArrayList<T> getAll(){
        return out;
    }

    public Iterator<T> iterator(){
        return out.iterator();
    };
}
