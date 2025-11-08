package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class ImplIterableWithPolicy<T> implements IterableWithPolicy<T>{
    private List<T> elements = new ArrayList<>();
    private Predicate<T> filter;   
   
    /*public ImplIterableWithPolicy(final T[] elements) {
        this.elements = List.of(elements);  //viene creata una lista i cui elementi sono quelli dell'array
    }*/

    public ImplIterableWithPolicy(final T[] elements) {        
        this(elements, new Predicate<T>() {
            public boolean test(T elem){
                return true;
            }
        });
    }

    public ImplIterableWithPolicy(final T[] elements, final Predicate<T> filter){
        this.elements = List.of(elements);
        this.filter = filter;
    }

    public void setIterationPolicy(final Predicate<T> filter){
        this.filter = filter;
    }

    //inner class parte 1
    /*public class ImplIterator implements Iterator<T>{ //il T deve essere lo stesso di impleme.. quindi non va scritto implIterator <T>, è come se stessi sovrascrivendo il tipo
        private int curr;
       
        public ImplIterator(){
            this.curr = 0;
        }

        public boolean hasNext(){
            return elements.size() > curr;
        }

        public T next(){ 
            if(hasNext()){
                return elements.get(curr++);
            } else{
                throw new NoSuchElementException();
            }
        }
    }
    
    public ImplIterator iterator(){
        return new ImplIterator();
    }*/

    //inner class parte 2
    public class ImplIterator implements Iterator<T>{ //il T deve essere lo stesso di impleme.. quindi non va scritto implIterator <T>, è come se stessi sovrascrivendo il tipo
        private int curr;
       
        public ImplIterator(){
            this.curr = 0;
        }

        @Override
        public boolean hasNext(){
            while(elements.size() > curr){
                if(filter.test(elements.get(curr))){
                    return true;
                }
                curr++;
            }
            return false;
        }

        @Override
        public T next(){ 
            if(hasNext()){
                return elements.get(curr++);
            } else{
                throw new NoSuchElementException();
            }
        }
    }
    
    public ImplIterator iterator(){
        return new ImplIterator();
    }
    
}
