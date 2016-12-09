package utils;

import utils.ExecStack;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by glinut on 11/1/2016.
 */
public class ExecStackImpl<T> implements ExecStack<T> {
    private Stack<T> stack;

    public ExecStackImpl() {
        this.stack = new Stack<T>();
    }

    @Override
    public void push(T elem) {
        stack.push(elem);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public T top() {
        return stack.peek();
    }

    public void setAll(ArrayList<T> list){
        Stack<T> newStack = new Stack<T>();
        while(list.size()!=0){
            T elem = list.get(0);
            list.remove(0);
            newStack.push(elem);
        }
        this.stack = newStack;
    }

    public Stack<T> getAll(){
        return stack;
    }
    public String toString(){
        StringBuffer stringBuffer= new StringBuffer();
        for (T string:stack){
            stringBuffer.append(string+",");
        }
        return stringBuffer.toString();
    }
}
