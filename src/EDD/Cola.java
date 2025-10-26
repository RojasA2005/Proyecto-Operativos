/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import ComponenteOS.PCB;

/**
 *
 * @author Andrés
 */
public class Cola {
    Nodo pFirst;
    private int size;
    
    public Cola(){
        pFirst = null;
        size = 0;
    }
    
    public Nodo peek(){
        if(pFirst == null){
            return null;
        }
        return pFirst;
    }
    
    public void queue(Nodo n){
        if(pFirst == null){
            pFirst = n;
        } else{
            Nodo aux = pFirst;
            while(aux.getpNext() != null){
                aux = aux.getpNext();
            }
            aux.setpNext(n);
        }
        size++;
    }
    
    public Nodo dequeue(){
        if(pFirst == null){
            return null;
        } 
        Nodo n = pFirst;
        pFirst = pFirst.getpNext();
        size--;
        return n;
    }
    
    public Nodo get(String name){
        Nodo n = this.pFirst;
        Nodo a;
        if(n == null){
            return n;
        }
        if(n.getData().getName().equals(name)){
            this.pFirst = this.pFirst.getpNext();
            return n;
        } 
        while(n.getpNext()!=null){
            if(n.getpNext().getData().getName().equals(name)){
                a = n.getpNext();
                n.setpNext(n.getpNext().getpNext());
                return a;
            }
            n = n.getpNext();
        }
        return null;
    }
    
    public void Eliminate(int id){
         Nodo n = this.pFirst;
         if(n!=null){
        if(n.getData().getId() == id){
            pFirst = pFirst.getpNext();
        } else{
            while(n.getpNext() != null){
                if(n.getpNext().getData().getId() == id){
                    n.setpNext(n.getpNext().getpNext());
                    return;
                }
                n = n.getpNext();
            }
        }
         } 
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
