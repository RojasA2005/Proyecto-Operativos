/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

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

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
