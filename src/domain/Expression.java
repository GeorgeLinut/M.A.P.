package domain;

import utils.Heap;
import utils.SymbolTable;

import java.io.Serializable;

/**
 * Created by glinut on 11/1/2016.
 */
public interface Expression extends Serializable{
    public int evaluate(SymbolTable<String,Integer> s, Heap<Integer,Integer> heap);
}
