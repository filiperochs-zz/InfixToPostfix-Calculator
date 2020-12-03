package controller;

public class No<T> {
    private T item;
    private No anterior;
    
    public No() {
        
    }
    
    public No(T item, No anterior) {
        this.item = item;
        this.anterior = anterior;
    }
    
    public T getItem() {
        return this.item;
    }
    
    public void setItem(T item) {
        if (item == null) {
            throw new NullPointerException("Não é possível inserir item nulo.");
        }
        
        this.item = item;
    }
    
    public No getAnterior() {
        return this.anterior;
    }
    
    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }
}
