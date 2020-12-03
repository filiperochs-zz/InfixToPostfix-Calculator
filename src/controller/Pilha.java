/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

public class Pilha<T> implements Stack<T> {
    
    public No<T> top;
    
    
    @Override
    public void push(T item) {
        No<T> novo = new No();
        novo.setItem(item);
        novo.setAnterior(top);
        this.top = novo;
    }

    @Override
    public T pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Pilha Vazia.");
        }
        
        No<T> aux = this.top;
        this.top = this.top.getAnterior();
        return aux.getItem();
    }
    
    @Override
    public boolean isEmpty() {
        if (this.top == null)
            return true;
        else
            return false;
    }

    @Override
    public int size() {
        No<T> p;
        p = this.top;
        int cont = 0;
        
        while (p != null) {
            p = p.getAnterior();
            cont++;
        }
        
        return cont;
    }
    
    @Override
    public T top() throws Exception {
        if (isEmpty())
            throw new Exception("Pilha Vazia.");
        
        return this.top.getItem();
    }
    
    public void clear() {
        this.top = null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        if (!isEmpty()) {
            
            No<T> p;
            p = this.top;

            while (p != null) {
                
//                if (cont == 70) {
//                    sb.append('\n');
//                    cont = 0;
//                }
                
                sb.append(p.getItem());
                p = p.getAnterior();
            }
            
            sb.reverse();
            
            int cont = 0;
            
            for (int i = 0; i < sb.length(); i++) {
                if (cont == 70) {
                    sb.insert(i, '\n');
                    cont = 0;
                }
                cont++;
            }
            
        } else {
            sb.append("Pilha Vazia.");
        }
        
        return sb.toString();
    }
}
