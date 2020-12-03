package controller;

public interface Stack<T> {
    public void push(T item) throws Exception;
    
    public T pop() throws Exception;
    
    public boolean isEmpty();
    
    public int size();
    
    public T top() throws Exception;
}
